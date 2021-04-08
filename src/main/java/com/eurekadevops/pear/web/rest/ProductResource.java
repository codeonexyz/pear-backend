package com.eurekadevops.pear.web.rest;

import com.eurekadevops.pear.service.ProductService;
import com.eurekadevops.pear.service.dto.ProductDto;
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
public class ProductResource {
    
    @Autowired
    private ProductService productService;
    
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (productDTO.getId() != null) throw new BadRequestAlertException("id already exisits");
        ProductDto result = productService.save(productDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
    @PutMapping("/products")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (productDTO.getId() == null) throw new BadRequestAlertException("invalid id");
        ProductDto result = productService.save(productDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> result = productService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        Optional<ProductDto> result = productService.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot get object: Product with id '{}' not found", id));
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }
    
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
