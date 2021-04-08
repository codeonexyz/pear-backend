package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.Order;
import com.eurekadevops.pear.service.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OrderDetailsMapper.class })
public interface OrderMapper {
    
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "user", ignore = true)
    Order toEntity(OrderDto orderDTO);
    
    @Mapping(source = "customer.id", target = "customerId")
    OrderDto toDTO(Order order);
}
