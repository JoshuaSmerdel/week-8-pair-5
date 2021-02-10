package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;
@Component
public class TransfersSqlDAO implements TransfersDAO

{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Transfers sendBucks(int accountFrom, int accountTo, BigDecimal transferAmount)
	{
		Transfers transfers = null;
		try
		{
			// get  the next transfer_id
			int transferId = getNextTransferId();
	
			// TODO build the Transfer object
			String transfersql = "BEGIN TRANSACTION; "
					+ ""
					+ "INSERT INTO transfers (transfer_id,transfer_type_id, transfer_status_id, account_from, account_to, amount)\r\n" + 
					"VALUES (?, 2, 2, ?, ?, ?);\r\n "
					+ "\r\n"
					+ "UPDATE accounts\r\n" 
					+"SET balance = balance - ?\r\n"
					+"WHERE user_id = ?;\r\n"
					+ "\r\n"
					+ "UPDATE accounts\r\n"
					+"SET balance = balance + ?\r\n"
					+"WHERE user_id = ?;\r\n"
					+ "COMMIT;";
			jdbcTemplate.update(transfersql, transferId, accountFrom, accountTo,transferAmount, transferAmount, accountFrom, transferAmount, accountTo);
			
			transfers = new Transfers();
			transfers.setTransferId(transferId);
			transfers.setTransferStatusId(2);
			transfers.setAccountFrom(accountFrom);
			transfers.setAccountTo(accountTo);
			transfers.setTransferTypeId(2);
			transfers.setTransferAmount(transferAmount);
		}
		catch(Exception ex)
		{
			System.err.println(ex.getMessage());
		}
		return transfers;
	}
														
	
	public List<User> listUser() 
	{
		List<User> users = new ArrayList<User>();
		
		return users;
	}

	
	
	private int getNextTransferId()
    {
        String sql = "SELECT nextval('seq_transfer_id') AS transfer_id;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
        if (row.next())
        {
            return row.getInt("transfer_id");
        }

        throw new RuntimeException("TransferId could not be generated.");
    }
	
	
	
	
	private Transfers mapRowToTransfers(SqlRowSet row)
	{
		Transfers transfers = new Transfers();
		transfers.setTransferId(row.getInt("transfer_id"));
		transfers.setTransferTypeId(row.getInt("transfer_type_id"));
		transfers.setTransferStatusId(row.getInt("transfer_status_id"));
		transfers.setAccountFrom(row.getInt("account_from"));
		transfers.setAccountTo(row.getInt("account_to"));
		transfers.setTransferStatusDesc(row.getString("transfer_status_desc"));
		transfers.setTransferTypeDesc(row.getString("transfer_type_desc"));
		transfers.setTransferAmount(row.getBigDecimal("amount"));
		
		return transfers;
	}



	




}
