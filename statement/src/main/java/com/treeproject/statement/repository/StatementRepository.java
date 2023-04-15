package com.treeproject.statement.repository;

import com.treeproject.statement.model.entity.Statement;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatementRepository {
    List<Statement> findAll();

    Optional<Statement> findById(Integer id);

    List<Statement> findAllBetweenDatesByAccountId(String from, String to, Integer id);

    List<Statement> findAllBetweenAmountsByAccountId(Integer fromAmount, Integer toAmount, Integer id);

    List<Statement> findAllBetweenDatesAndAmountsByAccountId(String from, String to, Integer fromAmount, Integer toAmount, Integer id);
}
