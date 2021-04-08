package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.Supplier;
import com.eurekadevops.pear.service.dto.SupplierDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SupplierMapper {
    
    Supplier toEntity(SupplierDto supplierDTO);
    
    SupplierDto toDTO(Supplier supplier);

}
