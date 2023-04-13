package com.treeproject.statement.repository;

import com.treeproject.statement.model.entity.Statement;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatementRepository {
    List<Statement> findAll();

    Optional<Statement> findById(Integer id);
    List<Statement> findAllBetweenDatesByAccountId(String from, String to,Integer id);
}
