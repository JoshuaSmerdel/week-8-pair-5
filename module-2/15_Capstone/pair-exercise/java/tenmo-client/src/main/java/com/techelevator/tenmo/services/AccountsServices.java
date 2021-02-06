package com.techelevator.tenmo.services;

import java.math.BigDecimal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

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
		
		HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
    	Accounts accounts = restTemplate.exchange(url, HttpMethod.GET, entity, Accounts.class).getBody();
		

		
		return accounts;
	}
	
	public Accounts getUpdatedBalanceFromSender(int userId,BigDecimal amtTransfrd)
	{
		String url = BASE_URL;
		
		HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
    	Accounts accounts = restTemplate.exchange(url, HttpMethod.GET, entity, Accounts.class).getBody();
		
		return accounts;
	}
	
	public Accounts getReceiversNewBalance(int receiversacctId,BigDecimal amtTransfrd)
	{
		String url = BASE_URL;
		
		HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
    	Accounts accounts = restTemplate.exchange(url, HttpMethod.GET, entity, Accounts.class).getBody();
		
		return accounts;
	}
}
