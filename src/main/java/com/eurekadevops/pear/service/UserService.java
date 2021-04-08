package com.eurekadevops.pear.service;

import com.eurekadevops.pear.domain.User;
import com.eurekadevops.pear.service.mapper.UserMapper;
import com.eurekadevops.pear.web.rest.exceptions.UniqueEmailException;
import com.eurekadevops.pear.web.rest.exceptions.UserNotFoundException;
import com.eurekadevops.pear.domain.Authority;
import com.eurekadevops.pear.repository.AuthorityRepository;
import com.eurekadevops.pear.repository.UserRepository;
import com.eurekadevops.pear.security.AuthoritiesConstants;
import com.eurekadevops.pear.service.dto.UserDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;
    
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserNotFoundException(String.format("The email '%s' does not exists.", email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), toSimpleGrantedAuthority(user.getAuthorities()));
    }

    protected Collection<SimpleGrantedAuthority> toSimpleGrantedAuthority(Set<Authority> authorities) {
        return authorities.stream().map((Authority authority) -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority.getName());
            return simpleGrantedAuthority;
        }).collect(Collectors.toList());
    }
    
    public UserDto save(UserDto userDTO) {
        try {
            User user = userMapper.toEntity(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setActivated(true);
            user.getAuthorities().add(authorityRepository.findByName(AuthoritiesConstants.USER));
            userRepository.save(user);
            return userMapper.toDTO(user);
        } catch (RuntimeException exc) {
            throw new UniqueEmailException(String.format("Email address '%s' already exists.", userDTO.getEmail()));
        }
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
