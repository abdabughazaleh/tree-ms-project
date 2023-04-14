package com.treeproject.statement.service;

import com.treeproject.statement.model.dto.StatementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatementService {
    List<StatementDTO> findAllBetweenDatesByAccountId(String from, String to, Integer id);

    List<StatementDTO> findAllBetweenAmountsByAccountId(String from, String to, Integer id);

    List<StatementDTO> findAllBetweenDatesAndAmountsByAccountId(String from, String to, Integer fromAmount, Integer toAmount, Integer id);

    List<StatementDTO> findStatement(String from, String to, Integer fromAmount, Integer toAmount, Integer id);
}
