package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountsSqlDao implements AccountsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Accounts getBalance() {
        Accounts accounts = null;
        String sql = "select balance\n" +
                "from accounts\n" +
                "where user_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        if (row.next())
        {
            accounts = mapRowToAccount(row);

        }

        return accounts;

    }
    private Accounts mapRowToAccount(SqlRowSet row)
    {
        Accounts accounts = new Accounts();

        accounts.setAccountId(row.getInt("account_id"));
        accounts.setUserId(row.getInt("user_id"));
        accounts.setBalance(row.getBigDecimal("balance"));
        
        return accounts;
    }

}
