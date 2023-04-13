package com.treeproject.account.repository;

import com.treeproject.account.model.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository {
    List<Account> findAll();

    Optional<Account> findById(Integer id);
}
