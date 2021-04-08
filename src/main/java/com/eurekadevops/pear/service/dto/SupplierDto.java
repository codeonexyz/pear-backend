package com.eurekadevops.pear.service.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SupplierDto {

    private Long id;

    @NotNull 
    @Size(max = 255)
    private String name;
    
    @NotNull 
    @Size(max = 255)
    private String address;
    
    @NotNull 
    @Size(max = 16)
    private String phone;

    @NotNull
    @Size(max = 255)
    private String email;
    
    @NotNull 
    @Size(max = 255)
    private String otherDetails;
}
