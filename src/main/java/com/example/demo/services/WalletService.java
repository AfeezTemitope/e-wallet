package com.example.demo.services;

import com.example.demo.Exceptions.WalletNotFoundException;
import com.example.demo.dto.request.WalletDepositRequest;
import com.example.demo.dto.response.WalletDepositResponse;

public interface WalletService {
    WalletDepositResponse deposit(WalletDepositRequest walletDepositRequest) throws WalletNotFoundException;
}
