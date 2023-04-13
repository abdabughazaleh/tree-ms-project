package com.treeproject.statement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statement {
    private Integer statementId;
    private Integer accountId;
    private String dateField;
    private BigDecimal amount;
}
