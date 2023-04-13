package com.treeproject.account.service.impl;

import com.treeproject.account.model.dto.AccountDto;
import com.treeproject.account.model.entity.Account;
import com.treeproject.account.model.mapper.AccountMapper;
import com.treeproject.account.repository.impl.AccountRepositoryImpl;
import com.treeproject.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepositoryImpl accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> all = this.accountRepository.findAll();
        return this.accountMapper.toDtos(all);
    }

    @Override
    public AccountDto getAccountById(Integer id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isPresent()){
            return this.accountMapper.toDto(account.get());
        }
        return null;
    }
}
