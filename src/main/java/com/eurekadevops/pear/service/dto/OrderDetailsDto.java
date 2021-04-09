package com.eurekadevops.pear.service.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    
    @NotNull
    private Double unitPrice;
    
    @NotNull
    private Integer size;
    
    @NotNull
    private Integer quantity;
    
    private Integer discount;
    
    private Double total;
    
    @NotNull
    private Long orderId;
    
    @NotNull
    private Long productId;
}
