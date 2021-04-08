package com.eurekadevops.pear.service;

import com.eurekadevops.pear.domain.Customer;
import com.eurekadevops.pear.repository.CustomerRepository;
import com.eurekadevops.pear.service.dto.CustomerDto;
import com.eurekadevops.pear.service.mapper.CustomerMapper;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    public CustomerDto save(CustomerDto customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }
    
    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }
    
    public Optional<CustomerDto> findById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toDTO);
    }
    
    public void delete(Long id) {
        Optional<Customer> result = customerRepository.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot delete: Customer with id '{id}' not found", id));
        customerRepository.delete(result.get());
    }

}
