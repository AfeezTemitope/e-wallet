package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequest {

    private Long transactionId;
    private String transactionType;
    private String transactionStatus;
    private BigDecimal transactionAmount;
    private LocalDate transactionDate;
    private String accountNumber;
    private String senderAccountNumber;
    private String receiverAccountNumber;
}
