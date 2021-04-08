package com.eurekadevops.pear.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    
    @NotNull
    @Size(max = 255)
    private String otherDetails;
    
    @NotNull
    @Size(max = 255)
    private String orderType;
    
    private Long customerId;
    
    private PaymentDto payment;
    
    private Set<OrderDetailsDto> orderDetails = new HashSet<>();

}
