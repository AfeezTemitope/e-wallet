package com.example.demo.services;


import com.example.demo.Exceptions.WalletNotFoundException;
import com.example.demo.dto.request.WalletDepositRequest;
import com.example.demo.dto.response.WalletDepositResponse;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    //@Sql(scripts = {"/db/data.sql"})
    public void testThatWalletServiceCanDeposit() throws WalletNotFoundException {
        Long walledId = 200L;
        BigDecimal depositAmount = new BigDecimal("5000.00");
        WalletDepositRequest walletDepositRequest = new WalletDepositRequest();
        walletDepositRequest.setId(walledId);
        walletDepositRequest.setAmount(depositAmount);

        WalletDepositResponse walletDepositResponse = walletService.deposit(walletDepositRequest);
        assertNotNull(walletDepositResponse);
        assertNotNull(walletDepositResponse.getStatus());
        assertEquals("SUCCESS", walletDepositResponse.getStatus());


    }
}
