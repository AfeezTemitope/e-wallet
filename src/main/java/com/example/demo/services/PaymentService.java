package com.example.demo.services;


import com.example.demo.dto.request.InitiateTransactionRequest;
import com.example.demo.dto.response.InitiateTransactionResponse;

public interface PaymentService {
    InitiateTransactionResponse makeTransfer(InitiateTransactionRequest request);
}
