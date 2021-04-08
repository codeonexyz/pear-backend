package com.eurekadevops.pear.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    
    @NotNull
    @Size(max = 255)
    private String firstName;
    
    @NotNull
    @Size(max = 255)
    private String lastName;
    
    @NotNull
    @Size(max = 255)
    private String address;
    
    @NotNull
    @Size(max = 255)
    private String phone;
    
    @NotNull
    @Size(max = 255)
    private String email;
}
