package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping()
    public Transfers sendBucks(){
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public Transfers listUsers(){
        return null;
    }

}
