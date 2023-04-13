package com.treeproject.statement.repository;

import com.treeproject.statement.model.entity.Statement;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StatementRepositoryImpl implements StatementRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Statement> findAll() {
        List<Statement> accounts = this.jdbcTemplate.query("SELECT * FROM statement", new RowMapper<Statement>() {
            @Override
            public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Statement(
                        rs.getInt("ID"),
                        rs.getInt("account_id"),
                        rs.getString("datefield"),
                        rs.getBigDecimal("amount")
                );
            }
        });
        return accounts;
    }

    @Override
    public Optional<Statement> findById(Integer id) {
        String query = String.format("SELECT * FROM statement WHERE ID = %s", id);
        Statement account = this.jdbcTemplate.queryForObject(query, new RowMapper<Statement>() {
            @Override
            public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Statement(
                        rs.getInt("ID"),
                        rs.getInt("account_id"),
                        rs.getString("datefield"),
                        rs.getBigDecimal("amount")
                );
            }
        });
        return Optional.ofNullable(account);
    }

    @Override
    public List<Statement> findAllBetweenDatesByAccountId(String from, String to, Integer id) {
        String query = String.format("SELECT * FROM statement WHERE Format((Replace(datefield,'.','/'), 'yyyy/mm/dd')) between Format((Replace('01.01.2020','.','/'), 'yyyy/mm/dd')) and  Format(Replace('09.08.2020','.','/'), 'yyyy/mm/dd')) and account_id = '%s' LIMIT 5", id);
        List<Statement> statements = this.jdbcTemplate.query(query, new RowMapper<Statement>() {
            @Override
            public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Statement(
                        rs.getInt("ID"),
                        rs.getInt("account_id"),
                        rs.getString("datefield"),
                        rs.getBigDecimal("amount")
                );
            }
        });
        return statements;
    }
}
