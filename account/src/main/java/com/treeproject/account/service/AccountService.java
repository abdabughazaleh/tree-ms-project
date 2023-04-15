package com.treeproject.account.service;

import com.treeproject.account.model.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<AccountDto> getAllAccounts();
    List<AccountDto> getAllAccountsEncrypted();
    AccountDto getAccountById(Integer id);
    AccountDto getEncryptedAccount(String id);
}
