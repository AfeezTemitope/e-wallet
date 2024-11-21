package com.example.demo.services;

import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Exceptions.WalletNotFoundException;
import com.example.demo.dto.request.DepositRequest;
import com.example.demo.dto.response.DepositResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    //@Sql(scripts =  {"/db/data.sql"})
    void testThatCustomerCanDeposit() throws UserNotFoundException, WalletNotFoundException {
        Long customerId = 200L;
        BigDecimal depositAmount = new BigDecimal("1000.00");
        String description = "this is just a test for deposit";
        DepositRequest depositRequest = buildDepositRequest(customerId, depositAmount, description);
        DepositResponse depositResponse = customerService.deposit(depositRequest);
        assertNotNull(depositResponse);
//        assertNotNull(depositResponse.getMessage());
        assertEquals("SUCCESS", depositResponse.getStatus());


    }

    private static DepositRequest buildDepositRequest(Long customerId, BigDecimal depositAmount, String description) {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setCustomerId(customerId);
        depositRequest.setAmount(depositAmount);
        depositRequest.setDescription(description);
        return depositRequest;
    }
}
