package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	
}
