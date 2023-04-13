package com.treeproject.account.service;

import com.treeproject.account.model.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<AccountDto> getAllAccounts();
    AccountDto getAccountById(Integer id);
}
