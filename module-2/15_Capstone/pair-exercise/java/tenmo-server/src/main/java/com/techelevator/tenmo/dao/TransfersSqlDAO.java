package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;
@Component
public class TransfersSqlDAO implements TransfersDAO

{

	private JdbcTemplate jdbcTemplate;

	
	public Transfers sendBucks(Transfers transfers)
	{
		// TODO Auto-generated method stub
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
		transfers.setTransferStatusId(row.getInt("transfer_status_id"));;
		transfers.setAccountFrom(row.getInt("account_from"));
		transfers.setAccountTo(row.getInt("account_to"));
		transfers.setTransferAmount(row.getBigDecimal("amount"));
		
		return transfers;
	}
}
