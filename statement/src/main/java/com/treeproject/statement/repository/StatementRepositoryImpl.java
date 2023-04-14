package com.treeproject.statement.repository;

import com.treeproject.statement.model.entity.Statement;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StatementRepositoryImpl implements StatementRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Statement> findAll() {
        String query = "SELECT * FROM statement";
        return getStatements(query);
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
                        rs.getString("amount")
                );
            }
        });
        return Optional.ofNullable(account);
    }

    @Override
    public List<Statement> findAllBetweenDatesByAccountId(String from, String to, Integer id) {
        String query = String.format("SELECT * FROM statement WHERE " +
                "   Format(replace(datefield,'.','/'), 'yyyy/mm/dd') between Format(replace('%s','-','/'), 'yyyy/mm/dd') and  Format(replace('%s','-','/'), 'yyyy/mm/dd') " +
                "  AND account_id ='%s' ", from, to, id);

        return getStatements(query);
    }

    @Override
    public List<Statement> findAllBetweenAmountsByAccountId(Integer fromAmount, Integer toAmount, Integer id) {
        String query = String.format("SELECT * FROM statement WHERE " +
                "  (val(amount) between %s AND %s) " +
                "  AND account_id ='%s' ", fromAmount, toAmount, id);
        return getStatements(query);
    }

    @Override
    public List<Statement> findAllBetweenDatesAndAmountsByAccountId(String from, String to, Integer fromAmount, Integer toAmount, Integer id) {
        String query = String.format("SELECT * FROM statement WHERE " +
                "  (val(amount) between %s AND %s) " +
                "  AND Format(replace(datefield,'.','/'), 'yyyy/mm/dd') between Format(replace('%s','-','/'), 'yyyy/mm/dd') and  Format(replace('%s','-','/'), 'yyyy/mm/dd') " +
                "  AND account_id ='%s' ", fromAmount, toAmount, from, to, id);
        return getStatements(query);
    }

    private List<Statement> getStatements(String query) {
        List<Statement> statements = this.jdbcTemplate.query(query, new RowMapper<Statement>() {
            @Override
            public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Statement(
                        rs.getInt("ID"),
                        rs.getInt("account_id"),
                        rs.getString("datefield"),
                        rs.getString("amount")
                );
            }
        });
        return statements;
    }

}
