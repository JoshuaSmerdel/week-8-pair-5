package com.techelevator.tenmo.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.User;

public class TransferServices extends ApiServiceBase {

    public TransferServices(String url)
    {
        super(url);
        this.BASE_URL = url + "transfers";
    }

    public Transfers sendBucks()
    {
        String url = BASE_URL;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity entity = new HttpEntity<Transfers>(headers);
        Transfers transfers = restTemplate.postForObject(url, entity, Transfers.class);



        return transfers;
    }

//    public List<User> listUser()
//    {
//        String url = BASE_URL;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(user.getToken());
//        HttpEntity entity = new HttpEntity<>(headers);
//        User[] users = restTemplate.getForObject(url + "users", entity, User[].class);
//
//
//
//        return users;
//    }
}