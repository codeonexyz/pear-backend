package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.Customer;
import com.eurekadevops.pear.service.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper {
    
    Customer toEntity(CustomerDto customerDTO);
    
    CustomerDto toDTO(Customer customer);

}
