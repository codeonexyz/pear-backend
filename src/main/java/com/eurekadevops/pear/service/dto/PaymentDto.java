package com.eurekadevops.pear.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    
    private Long id;
    
    @NotNull
    @Size(max = 255)
    private String paymentType;
    
    @NotNull
    @Size(max = 255)
    private String otherDetails;

}
