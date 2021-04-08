package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.Category;
import com.eurekadevops.pear.service.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper {
    
    Category toEntity(CategoryDto categoryDTO);

    CategoryDto toDTO(Category category);
}
