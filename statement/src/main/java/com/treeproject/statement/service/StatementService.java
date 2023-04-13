package com.treeproject.statement.service;

import com.treeproject.statement.model.dto.StatementDTO;
import com.treeproject.statement.model.entity.Statement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatementService {
    List<StatementDTO> findAllBetweenDatesByAccountId(String from, String to, Integer id);
}
