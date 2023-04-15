package com.treeproject.identity.model.mapper;

import com.treeproject.identity.model.dto.UserDto;
import com.treeproject.identity.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto dto);

    @Mapping(target = "correlationId", ignore = true)
    UserDto toDto(User entity);
}
