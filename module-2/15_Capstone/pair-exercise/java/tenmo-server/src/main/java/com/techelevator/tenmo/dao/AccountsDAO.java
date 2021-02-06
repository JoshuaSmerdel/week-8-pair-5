package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;

import java.math.BigDecimal;

public interface AccountsDAO 
{

	Accounts getBalance(int userId);
	
	Accounts getUpdatedBalanceFromSender(int userId,BigDecimal amtTransfrd);
	
	Accounts getReceiversNewBalance(int receiversacctId,BigDecimal amtTransfrd);
}
