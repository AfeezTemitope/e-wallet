package com.example.demo.services;

import com.example.demo.config.AppConfig;
import com.example.demo.dto.request.InitiateTransactionRequest;
import com.example.demo.dto.response.InitiateTransactionResponse;
import com.example.demo.dto.response.PayStackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.http.HttpMethod.POST;

@Service
public class PayStackImpl implements PaymentService {

    @Autowired
    private AppConfig appConfig;

    @Override
    public InitiateTransactionResponse makeTransfer(InitiateTransactionRequest request) {
        //1. convert amount to kobo based on paystack old system
        request.setAmount(request.getAmount().multiply(BigDecimal.valueOf(100)));

        //paystack uri bby
        URI uri = URI.create(appConfig.getPayStackUrl() );



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(appConfig.getPayStackApiKey());


        RequestEntity<InitiateTransactionRequest> requestEntity =
                RequestEntity.post(uri).headers(headers).body(request);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PayStackResponse> initResponse = restTemplate.exchange(requestEntity, PayStackResponse.class);

        PayStackResponse payStackResponse = initResponse.getBody();
        System.out.println("Paystack Response: " + initResponse.getBody());

        if (payStackResponse == null || !payStackResponse.isStatus()) {
            throw new RuntimeException("PayStack transaction initialization failed!");
        }


        InitiateTransactionResponse initiateTransactionResponse = new InitiateTransactionResponse();
        initiateTransactionResponse.setMessage(payStackResponse.getMessage());
        initiateTransactionResponse.setAuthorizationUrl(payStackResponse.getData().getAuthorizationUrl());
        initiateTransactionResponse.setReference(payStackResponse.getData().getReference());

        return initiateTransactionResponse;
    }


}
