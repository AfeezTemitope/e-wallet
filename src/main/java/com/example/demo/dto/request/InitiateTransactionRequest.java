package com.example.demo.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class InitiateTransactionRequest {
    private String email;
    private BigDecimal amount;
    private String currency;
}
