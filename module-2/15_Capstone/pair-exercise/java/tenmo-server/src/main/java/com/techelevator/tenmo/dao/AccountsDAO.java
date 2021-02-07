package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;

import java.math.BigDecimal;

public interface AccountsDAO 
{

	BigDecimal getBalance(int userId);
	
	void getUpdatedBalanceFromSender(int userId,BigDecimal amtTransfrd);
	
	void getReceiversNewBalance(int receiversacctId,BigDecimal amtTransfrd);
}
