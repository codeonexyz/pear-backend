package com.eurekadevops.pear.web.rest;

import com.eurekadevops.pear.service.CustomerService;
import com.eurekadevops.pear.service.dto.CustomerDto;
import com.eurekadevops.pear.web.rest.exceptions.BadRequestAlertException;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import com.eurekadevops.pear.web.rest.exceptions.ValidationException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerResource {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto customerDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (customerDTO.getId() != null) throw new BadRequestAlertException("id already exitsts");
        CustomerDto result = customerService.save(customerDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
    @PutMapping("/customers")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid CustomerDto customerDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (customerDTO.getId() == null) throw new BadRequestAlertException("Invalid id");
        CustomerDto result = customerService.save(customerDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> result = customerService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        Optional<CustomerDto> result = customerService.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot fetch object: Cutomer with id '{}' not found", id));
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }
    
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
