package com.treeproject.identity.model.mapper;

import com.treeproject.identity.model.dto.TokenDto;
import com.treeproject.identity.model.entity.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    TokenDto toDto(Token entity);

    Token toEntity(TokenDto dto);
}
