package com.eurekadevops.pear.service.dto;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    @Size(max = 255)
    private String name;
    
    @NotEmpty
    @Size(max = 255)
    private String description;
    
    @NotEmpty
    @Size(max = 255)
    private String unit;
    
    @NotEmpty
    @NotNull
    private Integer quantity;
    
    @NotEmpty
    @NotNull
    private Double price;
    
    @NotEmpty
    @NotNull
    private Integer status;
    
    @Size(max = 255)
    private String otherDetails;
    
    @NotNull
    private Long supplierId;
    
    @NotNull
    private Long categoryId;

}
