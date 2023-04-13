package com.treeproject.statement.controller;

import com.treeproject.statement.model.dto.StatementDTO;
import com.treeproject.statement.service.StatementService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statements")
public class StatementController {
    private final StatementService statementService;

    @GetMapping("/get-all")
    public List<StatementDTO> get(){
        return this.statementService.findAllBetweenDatesByAccountId("2020.01.01", "2020.09.01" , 1);
    }
}
