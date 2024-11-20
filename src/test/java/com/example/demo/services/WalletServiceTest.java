package com.example.demo.services;


import com.example.demo.dto.request.WalletDepositRequest;
import com.example.demo.dto.response.WalletDepositResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    public void testThatWalletServiceCanDeposit(){
        Long walledId = 1L;
        BigDecimal depositAmount = new BigDecimal("1000.00");
        WalletDepositRequest walletDepositRequest = new WalletDepositRequest();
        walletDepositRequest.setId(walledId);
        walletDepositRequest.setAmount(depositAmount);

        WalletDepositResponse walletDepositResponse = walletService.deposit(walletDepositRequest);
        assertNotNull(walletDepositResponse);
        assertNotNull(walletDepositResponse.getStatus());
        assertEquals("SUCCESSFUL", walletDepositResponse.getStatus());


    }
}
