package com.example.demo.services;

import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Exceptions.WalletNotFoundException;
import com.example.demo.dto.request.DepositRequest;
import com.example.demo.dto.response.DepositResponse;

public interface CustomerService {
    DepositResponse deposit(DepositRequest depositRequest) throws UserNotFoundException, WalletNotFoundException;
}
