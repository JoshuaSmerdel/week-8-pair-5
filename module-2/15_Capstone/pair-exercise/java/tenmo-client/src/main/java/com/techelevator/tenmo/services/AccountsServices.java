package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Accounts;

public class AccountsServices extends ApiServiceBase
{
	public AccountsServices(String url)
	{
		super(url);
		this.BASE_URL = url + "accounts";
	}
	
	public Accounts getBalance()
	{
		String url = BASE_URL;
		Accounts accounts = restTemplate.getForObject(url, Accounts.class);
		
		return accounts;
	}
	
}
