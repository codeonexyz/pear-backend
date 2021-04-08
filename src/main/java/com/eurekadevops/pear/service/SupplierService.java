package com.eurekadevops.pear.service;

import com.eurekadevops.pear.domain.Supplier;
import com.eurekadevops.pear.repository.SupplierRepository;
import com.eurekadevops.pear.service.dto.SupplierDto;
import com.eurekadevops.pear.service.mapper.SupplierMapper;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    private SupplierMapper supplierMapper;
    
    private static final Logger logger = LoggerFactory.getLogger(SupplierService.class.getName());
    
    public SupplierDto save(SupplierDto supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        supplier = supplierRepository.save(supplier);
        return supplierMapper.toDTO(supplier);
    }
    
    public Optional<SupplierDto> findById(Long id) {
        return supplierRepository.findById(id).map(supplierMapper::toDTO);
    }
    
    public List<SupplierDto> getAll() {
        return supplierRepository.findAll().stream().map(supplierMapper::toDTO).collect(Collectors.toList());
    }
    
    public void delete(Long id) {
        Optional<Supplier> result = supplierRepository.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Supplier with id {} not found", id));
        supplierRepository.delete(result.get());
    }

}
