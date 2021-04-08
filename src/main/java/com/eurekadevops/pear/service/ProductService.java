package com.eurekadevops.pear.service;

import com.eurekadevops.pear.domain.Product;
import com.eurekadevops.pear.domain.Supplier;
import com.eurekadevops.pear.repository.CategoryRepository;
import com.eurekadevops.pear.repository.ProductRepository;
import com.eurekadevops.pear.repository.SupplierRepository;
import com.eurekadevops.pear.service.dto.ProductDto;
import com.eurekadevops.pear.service.mapper.ProductMapper;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductMapper productMapper;
    
    public ProductDto save(ProductDto productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product.setSupplier(supplierRepository.findById(productDTO.getSupplierId()).orElseThrow(() -> new NotFoundException("Supplier not found")));
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found")));
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }
    
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(productMapper::toDTO);
    }
    
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDTO).collect(Collectors.toList());
    }
    
    public void delete(Long id) {
        Optional<Product> result = productRepository.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot delete: Product with id '{}' not found", id));
        productRepository.delete(result.get());
    }
}
