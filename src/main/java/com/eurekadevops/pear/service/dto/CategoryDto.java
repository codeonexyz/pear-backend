package com.eurekadevops.pear.service.dto;

import javax.validation.constraints.NotEmpty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryDto {
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String description;
}
