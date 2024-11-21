package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositResponse {
    private Long transactionId;
    private String amount;
    private String message;
    private String status;
}
