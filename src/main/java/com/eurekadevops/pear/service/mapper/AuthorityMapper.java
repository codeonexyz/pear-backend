package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.Authority;
import com.eurekadevops.pear.service.dto.AuthorityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AuthorityMapper {
    Authority toEntity(AuthorityDto authorityDTO);
    AuthorityDto toDTO(Authority authority);
}
