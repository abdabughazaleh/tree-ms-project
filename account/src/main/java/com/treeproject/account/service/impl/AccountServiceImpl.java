package com.treeproject.account.service.impl;

import com.treeproject.account.model.dto.AccountDto;
import com.treeproject.account.model.entity.Account;
import com.treeproject.account.model.mapper.AccountMapper;
import com.treeproject.account.repository.impl.AccountRepositoryImpl;
import com.treeproject.account.service.AccountService;
import com.treeproject.account.utils.ASEEncrypt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<AccountDto> getAllAccountsEncrypted() {
        List<AccountDto> accounts = this.getAllAccounts();
        return accounts.stream()
                .peek(acc -> {
                    acc.setAccountNumber(ASEEncrypt.encrypt(acc.getAccountNumber()));
                }).collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(Integer id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isPresent()) {
            return this.accountMapper.toDto(account.get());
        }
        return null;
    }

    @Override
    public AccountDto getEncryptedAccount(String acc) {
        int decryptAccount = Integer.parseInt(Objects.requireNonNull(ASEEncrypt.decrypt(acc)));
        AccountDto account = getAccountById(decryptAccount);
        account.setAccountNumber(ASEEncrypt.decrypt(account.getAccountNumber()));
        return account;
    }
}
