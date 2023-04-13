package com.treeproject.account.repository.impl;

import com.treeproject.account.model.entity.Account;
import com.treeproject.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        List<Account> accounts = this.jdbcTemplate.query("SELECT * FROM account", new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Account(
                        rs.getInt("ID"),
                        rs.getString("account_type"),
                        rs.getString("account_number")
                );
            }
        });
        return accounts;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        String query = String.format("SELECT * FROM account WHERE ID = %s", id);
        Account account = this.jdbcTemplate.queryForObject(query, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Account(
                        rs.getInt("ID"),
                        rs.getString("account_type"),
                        rs.getString("account_number")
                );
            }
        });
        return Optional.ofNullable(account);
    }
}
