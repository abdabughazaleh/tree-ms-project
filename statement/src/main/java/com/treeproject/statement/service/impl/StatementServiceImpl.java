package com.treeproject.statement.service.impl;

import com.treeproject.statement.model.dto.StatementDTO;
import com.treeproject.statement.model.entity.Statement;
import com.treeproject.statement.model.mapper.StatementMapper;
import com.treeproject.statement.repository.StatementRepository;
import com.treeproject.statement.service.StatementService;
import com.treeproject.statement.utils.DDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;
    private final StatementMapper statementMapper;

    @Override
    public List<StatementDTO> findAllBetweenDatesByAccountId(String from, String to, Integer id) {
        List<Statement> stms = this.statementRepository.findAllBetweenDatesByAccountId(from, to, id);
        return this.statementMapper.toDtos(stms);
    }

    @Override
    public List<StatementDTO> findAllBetweenAmountsByAccountId(String from, String to, Integer id) {
        return null;
    }

    @Override
    public List<StatementDTO> findAllBetweenDatesAndAmountsByAccountId(String from, String to, Integer fromAmount, Integer toAmount, Integer id) {
        return null;
    }

    @Override
    public List<StatementDTO> findStatement(String from, String to, Integer fromAmount, Integer toAmount, Integer id) {
        List<Statement> statements;
        if ((from != null && to != null) && (fromAmount != null && toAmount != null)) {
            statements = this.statementRepository.findAllBetweenDatesAndAmountsByAccountId(from, to, fromAmount, toAmount, id);
        } else if (fromAmount != null && toAmount != null) {
            statements = this.statementRepository.findAllBetweenDatesByAccountId(from, to, id);
        } else if (from != null && to != null) {
            statements = this.statementRepository.findAllBetweenDatesByAccountId(from, to, id);
        } else {
            String fromDate = DDateUtils.getNowAsString();
            String toDate = DDateUtils.getPlushMonthsAsString(3);
            statements = this.statementRepository.findAllBetweenDatesByAccountId(fromDate, toDate, id);
        }

        return this.statementMapper.toDtos(statements);
    }
}
