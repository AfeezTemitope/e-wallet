package com.example.demo.dto.response;

import lombok.Data;

@Data
public class InitiateTransactionResponse {
    private String message;
    private String authorizationUrl;
    private String reference;
}
