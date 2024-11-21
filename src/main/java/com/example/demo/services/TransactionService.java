package com.example.demo.services;

import com.example.demo.dto.request.TransactionRequest;
import com.example.demo.dto.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse makeTransaction(TransactionRequest transactionRequest);

    TransactionResponse getTransactionBy(Long transactionId);
}
