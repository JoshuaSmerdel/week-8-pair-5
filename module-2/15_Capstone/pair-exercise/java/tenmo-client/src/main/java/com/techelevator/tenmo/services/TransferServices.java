package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.techelevator.tenmo.models.Transfers;

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
        HttpEntity entity = new HttpEntity<>(headers);
        Transfers transfers = restTemplate.exchange(url, HttpMethod.GET, entity, Transfers.class).getBody();



        return transfers;
    }

    public Transfers listUsers()
    {
        String url = BASE_URL;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        Transfers transfers = restTemplate.exchange(url, HttpMethod.GET, entity, Transfers.class).getBody();



        return transfers;
    }
}