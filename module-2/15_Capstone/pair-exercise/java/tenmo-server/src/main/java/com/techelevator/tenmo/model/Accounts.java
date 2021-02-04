package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Accounts {
	
	private int accountID;
	private int userID;
	private BigDecimal balance;
	
	
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public BigDecimal getBalanceBigDecimal() {
		return balance;
	}
	public void setBalanceBigDecimal(BigDecimal balance) {
		this.balance = balance;
	}

}
