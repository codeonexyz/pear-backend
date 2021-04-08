package com.eurekadevops.pear.service;

import com.eurekadevops.pear.domain.Category;
import com.eurekadevops.pear.repository.CategoryRepository;
import com.eurekadevops.pear.service.dto.CategoryDto;
import com.eurekadevops.pear.service.mapper.CategoryMapper;
import com.eurekadevops.pear.web.rest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;
    
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class.getName());

    public CategoryDto save(CategoryDto categoryDTO) {
        Category category =  categoryMapper.toEntity(categoryDTO);
        categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }
    
    public Optional<CategoryDto> findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDTO);
    }

    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }
    
    public void delete(Long id) {
        Optional<Category> result = categoryRepository.findById(id);
        if (!result.isPresent()) throw new NotFoundException(String.format("Cannot delete: Category with id '{id}' not found", id));
        categoryRepository.delete(result.get());
    }
}
