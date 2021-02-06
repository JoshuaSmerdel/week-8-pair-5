package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.dao.UserDAO;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.tenmo.model.Transfers;

@RestController
@RequestMapping(path="/transfers")




public class TransfersController {

    @Autowired
    TransfersDAO dao;
    @Autowired
    UserDAO userdao;

    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    public Transfers sendBucks(@RequestBody Transfers transfers, Principal principal){
    	String username = principal.getName();
		int userId = userdao.findIdByUsername(username);
		if (userId != transfers.getAccountFrom())
		{
			throw new RuntimeException("Invalid Account");
		}
		
		return dao.sendBucks(transfers.getAccountFrom(), transfers.getAccountTo(), transfers.getTransferAmount());//sendBucks function passes in account from, account
		//to and transferamount information. if I include that in my function, how do I incorporate
		//in line 32 above - that's why line 40 is red
		//**those arguments have been passed through in the function in both the 
		//TransfersSqlDAO and TransfersDAO
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public Transfers listUser(){
    	//may not need to make new method, can possibly use userDAO method list users
        return null;
    }

}
