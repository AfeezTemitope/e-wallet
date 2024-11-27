package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class WalletTransferRequest {

    private String bankName;
    private String AccountNumber;
    private String narration;
    private String Currencry;
    private BigDecimal amount;
    private String code;
    private String description;
}
