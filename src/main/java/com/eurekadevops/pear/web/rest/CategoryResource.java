package com.eurekadevops.pear.web.rest;

import com.eurekadevops.pear.service.CategoryService;
import com.eurekadevops.pear.service.dto.CategoryDto;
import com.eurekadevops.pear.web.rest.exceptions.BadRequestAlertException;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import com.eurekadevops.pear.web.rest.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
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
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryResource.class.getName());

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (categoryDTO.getId() != null) throw new BadRequestAlertException("id already exists");
        CategoryDto newCategory = categoryService.save(categoryDTO);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }
    
    @PutMapping("/categories")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
        if (categoryDTO.getId() == null) throw new BadRequestAlertException("invalid id");
        CategoryDto result = categoryService.save(categoryDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
        Optional<CategoryDto> result = categoryService.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot find: Category with id '{}' not found", id));
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }
    
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
