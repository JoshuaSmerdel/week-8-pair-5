package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;

public interface TransfersDAO 
{
	Transfers sendBucks(Transfers transfers);
	
	List<User> listUser();
}
