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

	private JdbcTemplate jdbcTemplate;

	
	public Transfers sendBucks(int accountFrom, int accountTo, BigDecimal transferAmount)
	{
		Transfers transfers = null;
		// get  the next transfer_id
		int transferId = getNextTransferId();

		// TODO build the Transfer object
		String transfersql = "BEGIN TRANSACTION; "
				+ ""
				+ "INSERT INTO transfers (transfer_id,transfer_type_id, transfer_status_id, account_from, account_to, amount)\r\n" + 
				"VALUES (2, 2, ?, ?, ?); "
				+ ""
				+ "UPDATE accounts\\r\\n\" + \r\n" 
				+"	   		\"SET balance = balance - ?\\r\\n\" + \r\n" 
				+"	   		\"WHERE user_id = ?;"
				+ ""
				+ "UPDATE accounts\\r\\n\" + \r\n"  
				+"	   		\"SET balance = balance + ?\\r\\n\" + \r\n"
				+"	   		\"WHERE account_id = ?;"
				+ "COMMIT;";
		jdbcTemplate.update(transfersql, transferId, accountFrom, accountTo,transferAmount, transferAmount, accountFrom, transferAmount, accountTo);
		
		return transfers;
	}
														

	
	public List<User> listUser(int userId)
	
	{
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo FROM transfers t  \r\n" + 
				"			INNER JOIN accounts a ON t.account_from = a.account_id  \r\n" + 
				"			INNER JOIN accounts b ON t.account_to = b.account_id  \r\n" + 
				"			INNER JOIN users u ON a.user_id = u.user_id  \r\n" + 
				"			INNER JOIN users v ON b.user_id = v.user_id  \r\n" + 
				"				WHERE a.user_id = ? OR b.user_id = ?;";
		
		SqlRowSet transferUsers = jdbcTemplate.queryForRowSet(sql, userId, userId);
		
		while(transferUsers.next())
		{
			Transfers transfer = mapRowToTransfers(transferUsers);
			//users.add(transfer);
		}
		 
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



	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return null;
	}




}
