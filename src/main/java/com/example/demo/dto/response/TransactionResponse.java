package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionResponse {

    private Long id;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private String amount;
    private LocalDate date;
    private String description;
    private String message;
    private String status;
}
