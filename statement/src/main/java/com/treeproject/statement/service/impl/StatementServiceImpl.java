package com.treeproject.statement.service.impl;

import com.treeproject.statement.model.dto.IsAllowedPermissionDto;
import com.treeproject.statement.model.dto.StatementDTO;
import com.treeproject.statement.model.entity.Statement;
import com.treeproject.statement.model.mapper.StatementMapper;
import com.treeproject.statement.proxy.IdentityProxy;
import com.treeproject.statement.repository.StatementRepository;
import com.treeproject.statement.service.StatementService;
import com.treeproject.statement.utils.DDateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;
    private final StatementMapper statementMapper;
    private final IdentityProxy identityProxy;

    @Override
    public List<StatementDTO> findAllBetweenDatesByAccountId(String from, String to, Integer id) {
        log.info("Getting findAllBetweenDatesByAccountId {} {} {} ", from, to, id);
        List<Statement> statements = this.statementRepository.findAllBetweenDatesByAccountId(from, to, id);
        log.info("Getting findAllBetweenDatesByAccountId {} {} {} {}", from, to, id, statements);
        return this.statementMapper.toDtos(statements);
    }

    @Override
    public List<StatementDTO> findAllBetweenAmountsByAccountId(Integer from, Integer to, Integer id) {
        log.info("Getting findAllBetweenAmountsByAccountId {} {} {}", from, to, id);
        List<Statement> statements = this.statementRepository.findAllBetweenAmountsByAccountId(from, to, id);
        log.info("Getting findAllBetweenAmountsByAccountId {} {} {} {}", from, to, id, statements);
        return this.statementMapper.toDtos(statements);
    }

    @Override
    public List<StatementDTO> findAllBetweenDatesAndAmountsByAccountId(String from, String to, Integer fromAmount, Integer toAmount, Integer id) {
        log.info("Getting findAllBetweenDatesAndAmountsByAccountId {} {} {}", from, to, id);
        List<Statement> statements = this.statementRepository.findAllBetweenDatesAndAmountsByAccountId(from, to, fromAmount, toAmount, id);
        log.info("Getting findAllBetweenDatesAndAmountsByAccountId {} {} {} {}", from, to, id, statements);
        return this.statementMapper.toDtos(statements);
    }

    @Override
    public List<StatementDTO> findStatement(String from, String to, Integer fromAmount, Integer toAmount, Integer id, String token) {
        log.info("Getting findStatement fromDate {} toDate {} fromAmount {}  toAmount {} Account {} ", from, to, fromAmount, toAmount, id);
        List<StatementDTO> statements;
        IsAllowedPermissionDto permissionDto = IsAllowedPermissionDto.builder().build();

        if ((from != null && to != null) && (fromAmount != null && toAmount != null)) {
            log.info("Getting findStatement (findAllBetweenDatesAndAmountsByAccountId) fromDate {} toDate {} fromAmount {}  toAmount {} Account {} ", from, to, fromAmount, toAmount, id);
            permissionDto.setPermission("date_range");
            this.identityProxy.isAllowed(permissionDto, token);
            statements = this.findAllBetweenDatesAndAmountsByAccountId(from, to, fromAmount, toAmount, id);
        } else if (fromAmount != null && toAmount != null) {
            log.info("Getting findStatement (findAllBetweenAmountsByAccountId) fromDate {} toDate {} fromAmount {}  toAmount {} Account {} ", from, to, fromAmount, toAmount, id);
            permissionDto.setPermission("amount_range");
            this.identityProxy.isAllowed(permissionDto, token);
            statements = this.findAllBetweenAmountsByAccountId(fromAmount, toAmount, id);
        } else if (from != null && to != null) {
            log.info("Getting findStatement (findAllBetweenDatesByAccountId) fromDate {} toDate {} fromAmount {}  toAmount {} Account {} ", from, to, fromAmount, toAmount, id);
            permissionDto.setPermission("date_range");
            this.identityProxy.isAllowed(permissionDto, token);
            statements = this.findAllBetweenDatesByAccountId(from, to, id);
        } else {
            log.info("Getting findStatement ELSE  (findAllBetweenDatesByAccountId)=> 3 months bank statements fromDate {} toDate {} fromAmount {}  toAmount {} Account {} ", from, to, fromAmount, toAmount, id);
            String fromDate = DDateUtils.getNowAsString();
            String toDate = DDateUtils.getPlushMonthsAsString(3);
            statements = this.findAllBetweenDatesByAccountId(fromDate, toDate, id);
        }

        return statements;
    }
}
