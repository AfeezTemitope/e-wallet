package com.example.demo.services;

import com.example.demo.Exceptions.WalletNotFoundException;
import com.example.demo.data.models.Wallet;
import com.example.demo.data.repository.WalletRepository;
import com.example.demo.dto.request.WalletDepositRequest;
import com.example.demo.dto.response.WalletDepositResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.example.demo.dto.response.TransactionStatus.SUCCESS;

@Service
@AllArgsConstructor
public class AppWalletService implements WalletService{

   // private final ModelMapper modelMapper;
    private final WalletRepository walletRepository;
    @Override
    public WalletDepositResponse deposit(WalletDepositRequest walletDepositRequest) throws WalletNotFoundException {
        //1. retrieve wallet
        Wallet wallet = walletRepository.findById(walletDepositRequest.getId())
                .orElseThrow(()-> new WalletNotFoundException("Wallet not found", walletDepositRequest.getId()));
        String.format("Wallet found with id %s", wallet.getId());
        wallet.setBalance(wallet.getBalance().add(walletDepositRequest.getAmount()));
        wallet = walletRepository.save(wallet);
        WalletDepositResponse walletDepositResponse = new WalletDepositResponse();
        walletDepositResponse.setStatus(SUCCESS.toString());
        walletDepositResponse.setAmount(walletDepositRequest.getAmount().toString());
       // modelMapper.map(walletDepositRequest, Wallet.class);
        return walletDepositResponse;
    }
}
