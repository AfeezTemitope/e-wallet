package com.example.demo.services;

import com.example.demo.dto.request.DepositRequest;
import com.example.demo.dto.response.DepositResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void testThatCustomerCanDeposit(){
        Long customerId = 1L;
        BigDecimal depositAmount = new BigDecimal("1000.00");
        String description = "this is just a test for deposit";
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setCustomerId(customerId);
        depositRequest.setAmount(depositAmount);
        depositRequest.setDescription(description);
        DepositResponse depositResponse = customerService.deposit(depositRequest);
        assertNotNull(depositResponse);
        assertNotNull(depositResponse.getMessage());
        assertEquals("SUCCESSFUL", depositResponse.getStatus());


    }
}
