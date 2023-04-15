package com.treeproject.account.controller;

import com.treeproject.account.model.dto.AccountDto;
import com.treeproject.account.utils.ASEEncrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("local")
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Test
    void getAllAccountsEncrypted() {
        ResponseEntity<List<AccountDto>> allAccountsEncrypted = this.accountController.getAllAccountsEncrypted();
        assertEquals("846Vd3YmTrFtcCQhrqg88A==", Objects.requireNonNull(allAccountsEncrypted.getBody().get(0).getAccountNumber()));
    }

    @Test
    void getAccountById() {
        ResponseEntity<AccountDto> account = this.accountController.getAccountById(1);
        assertEquals(1, Objects.requireNonNull(account.getBody()).getAccountId());
    }

    @Test
    void decrypt() {
        String account = "846Vd3YmTrFtcCQhrqg88A==";
        String decrypt = ASEEncrypt.decrypt(account);
        assertEquals("0012250016001", decrypt);
    }
    @Test
    void encrypt() {
        String account = "0012250016001";
        String encrypt = ASEEncrypt.encrypt(account);
        assertEquals("846Vd3YmTrFtcCQhrqg88A==", encrypt);
    }
}