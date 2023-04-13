package com.treeproject.statement.service.impl;

import com.treeproject.statement.model.dto.StatementDTO;
import com.treeproject.statement.model.entity.Statement;
import com.treeproject.statement.model.mapper.StatementMapper;
import com.treeproject.statement.repository.StatementRepository;
import com.treeproject.statement.service.StatementService;
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
}
