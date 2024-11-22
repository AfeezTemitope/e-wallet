package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DepositRequest {
    private Long customerId;
    private String sederAccountNumber;
    private BigDecimal amount;
    private String Description;
}
