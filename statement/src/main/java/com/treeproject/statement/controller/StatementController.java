package com.treeproject.statement.controller;

import com.treeproject.statement.model.dto.StatementDTO;
import com.treeproject.statement.service.StatementService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statements")
public class StatementController {
    private final StatementService statementService;

    @GetMapping("/get")
    public List<StatementDTO> get(@RequestParam(value = "fromDate", required = false) String fromDate,
                                  @RequestParam(value = "toDate", required = false) String toDate,
                                  @RequestParam(value = "fromAmount", required = false) Integer fromAmount,
                                  @RequestParam(value = "toAmount", required = false) Integer toAmount,
                                  @RequestParam(value = "accountId", required = false) Integer accountId) {
        return this.statementService.findStatement(fromDate, toDate, fromAmount, toAmount, accountId);
    }
}
