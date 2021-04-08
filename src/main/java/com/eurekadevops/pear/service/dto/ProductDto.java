package com.eurekadevops.pear.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    
    private Long id;
    
    @NotNull
    @Size(max = 255)
    private String name;
    
    @Size(max = 255)
    private String description;
    
    @Size(max = 255)
    private String unit;
    
    @NotNull
    private Integer quantity;
    
    @NotNull
    private Double price;
    
    @NotNull
    private Integer status;
    
    @Size(max = 255)
    private String otherDetails;
    
    @NotNull
    private Long supplierId;
    
    @NotNull
    private Long categoryId;

}
