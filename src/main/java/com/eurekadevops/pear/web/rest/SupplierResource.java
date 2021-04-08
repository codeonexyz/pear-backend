package com.eurekadevops.pear.web.rest;

import com.eurekadevops.pear.service.SupplierService;
import com.eurekadevops.pear.service.dto.SupplierDto;
import com.eurekadevops.pear.web.rest.exceptions.BadRequestAlertException;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import com.eurekadevops.pear.web.rest.exceptions.ValidationException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class SupplierResource {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/suppliers")
    public ResponseEntity<SupplierDto> createSupplier(@Valid @RequestBody SupplierDto supplierDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (supplierDTO.getId() != null) throw new BadRequestAlertException("id already exists");
        SupplierDto result = supplierService.save(supplierDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
    @PutMapping("/suppliers")
    public ResponseEntity<SupplierDto> udpateSupplier(@Valid @RequestBody SupplierDto supplierDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (supplierDTO.getId() == null) throw new BadRequestAlertException("invalid id");
        SupplierDto result = supplierService.save(supplierDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable("id") Long id) {
        Optional<SupplierDto> result = supplierService.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Supplier with id {} not found", id));
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }
    
    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierDto> result = supplierService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("id") Long id) {
        supplierService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
