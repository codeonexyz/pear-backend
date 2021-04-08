package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.Payment;
import com.eurekadevops.pear.service.dto.PaymentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PaymentMapper {
    
    Payment toEntity(PaymentDto paymentDTO);
    
    PaymentDto toDTO(Payment payment);

}
