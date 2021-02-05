package com.techelevator.tenmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.model.Accounts;

@RestController
@RequestMapping(path="/accounts")
public class AccountsController
{
	@Autowired
	AccountsDAO dao;

	@GetMapping()
	public Accounts getBalance()
	{
		Accounts accounts = dao.getBalance();
				
		return accounts;
	}
}
	