package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.OrderDetails;
import com.eurekadevops.pear.service.dto.OrderDetailsDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface OrderDetailsMapper {
    
    @Mapping(target = "primaryKey", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderDetails toEntity(OrderDetailsDto orderDetails);
    
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    OrderDetailsDto toDTO(OrderDetails orderDetails);
    
    List<OrderDetails> toEntityList(List<OrderDetailsDto> orderDetailsList);

}
