package com.example.demo.services;

import com.example.demo.dto.request.DepositRequest;
import com.example.demo.dto.response.DepositResponse;
import org.springframework.stereotype.Service;

@Service
public class WolletCustomerService  implements CustomerService{

    //private final WalletService walletService;
    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
        return null;
    }
}
