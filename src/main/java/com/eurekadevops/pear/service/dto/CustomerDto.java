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
public class CustomerDto {
    private Long id;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String firstName;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String lastName;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String address;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String phone;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String email;
}
