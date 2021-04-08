package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.Product;
import com.eurekadevops.pear.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper {
    
    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDto productDTO);
    
    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDTO(Product product);
    
}
