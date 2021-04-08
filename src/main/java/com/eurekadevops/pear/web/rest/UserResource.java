package com.eurekadevops.pear.web.rest;

import com.eurekadevops.pear.domain.User;
import com.eurekadevops.pear.security.jwt.TokenProvider;
import com.eurekadevops.pear.service.dto.JwtDto;
import com.eurekadevops.pear.web.rest.exceptions.AuthenticationException;
import com.eurekadevops.pear.web.rest.exceptions.ValidationException;
import com.eurekadevops.pear.service.UserService;
import com.eurekadevops.pear.service.dto.AuthenticationDto;
import com.eurekadevops.pear.service.dto.UserDto;

import java.security.Principal;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenProvider tokenProvider;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationDto authenticationDTO, BindingResult result) throws Exception {
        if (result.hasErrors()) throw new ValidationException(result);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword()));
        } catch (BadCredentialsException exc) {
            throw new AuthenticationException("badCredentials", "Incorrect username or password");
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationDTO.getUsername());
        return ResponseEntity.ok(new JwtDto(tokenProvider.generateToken(userDetails)));
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDTO, BindingResult result) {
        if (result.hasErrors()) throw new ValidationException(result);
        UserDto newUser = userService.save(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<?> getUser(Principal principal) {
        User loggedUser = userService.findByEmail(principal.getName());
        return ResponseEntity.ok(loggedUser);
    }

}
