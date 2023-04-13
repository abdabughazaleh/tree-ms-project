package com.treeproject.statement.model.mapper;

import com.treeproject.statement.model.dto.StatementDTO;
import com.treeproject.statement.model.entity.Statement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatementMapper {
    StatementDTO toDto(Statement statement);
    List<StatementDTO> toDtos(List<Statement> statements);
}
