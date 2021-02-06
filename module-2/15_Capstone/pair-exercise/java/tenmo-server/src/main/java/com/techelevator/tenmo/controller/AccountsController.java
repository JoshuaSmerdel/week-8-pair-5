package com.techelevator.tenmo.controller;

import java.math.BigDecimal;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Accounts;

@RestController
@RequestMapping(path="/accounts")
public class AccountsController
{
	@Autowired
	AccountsDAO dao;
	@Autowired
	UserDAO userdao;
	

	@PreAuthorize("isAuthenticated()")
	@GetMapping()
	public Accounts getBalance(Principal principal)
	{
		String username = principal.getName();
		int userId = userdao.findIdByUsername(username);
		Accounts accounts = dao.getBalance(userId);
				
		return accounts;
	}
	
	@PreAuthorize("isAuthenticated()")
//	@GetMapping()
	public Accounts getUpdatedBalanceFromSender(int userId,BigDecimal amtTransfrd)
	{
		
		Accounts accounts = dao.getUpdatedBalanceFromSender(userId, amtTransfrd);
		return accounts;
	}
	
	@PreAuthorize("isAuthenticated()")
//	@GetMapping()
	public Accounts getReceiversNewBalance(int receiversacctId,BigDecimal amtTransfrd)
	{
		Accounts accounts = dao.getReceiversNewBalance(receiversacctId, amtTransfrd);
		return accounts;
	}
}
	