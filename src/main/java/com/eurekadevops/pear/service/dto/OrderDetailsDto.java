package com.eurekadevops.pear.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    
    @NotNull
    @NotEmpty
    private Double unitPrice;
    
    @NotNull
    @NotEmpty
    private Integer size;
    
    @NotNull
    @NotEmpty
    private Integer quantity;
    
    private Integer discount;
    
    private Double total;
    
    @NotNull
    @NotEmpty
    private Long orderId;
    
    @NotNull
    @NotEmpty
    private Long productId;
}
