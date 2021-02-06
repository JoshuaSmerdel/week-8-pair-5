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
		Accounts accounts = null;
		String transfersql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)\r\n" + 
				"VALUES (2, 2, ?, ?, ?);";
		jdbcTemplate.update(transfersql, accountFrom, accountTo,transferAmount);
		
		return null;
	}
	

	
	public List<User> listUser()
	
	{
		List<User> users = new ArrayList<User>();
		 
		return users;
		
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
