package com.treeproject.account.controller;

import com.treeproject.account.model.dto.AccountDto;
import com.treeproject.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> allAccounts = this.accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/account")
    public ResponseEntity<AccountDto> getAccountById(@RequestParam Integer id) {
        AccountDto account = this.accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }
}
