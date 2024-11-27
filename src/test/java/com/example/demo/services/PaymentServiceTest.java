package com.example.demo.services;

import com.example.demo.dto.request.InitiateTransactionRequest;
import com.example.demo.dto.response.InitiateTransactionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void testThatTransferCanBeMade() {
        InitiateTransactionRequest request = new InitiateTransactionRequest();
        request.setEmail("test@example.com");
        request.setAmount(BigDecimal.valueOf(5000));

        InitiateTransactionResponse response = paymentService.makeTransfer(request);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getAuthorizationUrl()).isNotNull();
        assertThat(response.getReference()).isNotNull();
        System.out.println(response.getAuthorizationUrl() + response.getReference() + response.getMessage());
    }
}
