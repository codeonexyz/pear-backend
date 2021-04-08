package com.eurekadevops.pear.service.mapper;

import com.eurekadevops.pear.domain.User;
import com.eurekadevops.pear.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "activated", ignore = true)
    User toEntity(UserDto userDTO);

    UserDto toDTO(User user);
}
