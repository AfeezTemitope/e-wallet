package com.example.demo.services;

import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Exceptions.WalletNotFoundException;
import com.example.demo.data.models.Customer;
import com.example.demo.data.models.Wallet;
import com.example.demo.data.repository.CustomerRepository;
import com.example.demo.dto.request.DepositRequest;
import com.example.demo.dto.request.TransactionRequest;
import com.example.demo.dto.request.WalletDepositRequest;
import com.example.demo.dto.response.DepositResponse;
import com.example.demo.dto.response.TransactionStatus;
import com.example.demo.dto.response.WalletDepositResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.demo.dto.response.TransactionStatus.SUCCESS;

@Service
@AllArgsConstructor
public class WolletCustomerService  implements CustomerService{

    private final CustomerRepository customerRepository;
    private final WalletService walletService;
    private final ModelMapper modelMapper;
    private final TransactionService transactionsService;


    @Override
    public DepositResponse deposit(DepositRequest depositRequest) throws UserNotFoundException, WalletNotFoundException {
        Customer customer = getCustomerFor(depositRequest);
        Wallet customerWallet = customer.getWallet();
        WalletDepositRequest walletDepositRequest = createWalletDepositRequest(depositRequest, customerWallet);
        WalletDepositResponse response = walletService.deposit(walletDepositRequest);
        return createCustomerDepositResponse(depositRequest, response, customerWallet);
    }

    private DepositResponse createCustomerDepositResponse(DepositRequest depositRequest, WalletDepositResponse response, Wallet customerWallet) {
        DepositResponse depositResponse = new DepositResponse();
        //depositResponse.setMessage("deposit successful");
        depositResponse.setStatus(response.getStatus());
        TransactionRequest transactionRequest = createTransactionRequest(depositRequest, response, customerWallet);
        transactionsService.makeTransaction(transactionRequest);
        return depositResponse;

    }
//        var depositResponse = modelMapper.map(response, DepositResponse.class);
//        createTransactionRequest(depositRequest, response, customerWallet);
//        transactionsService.makeTransaction(modelMapper.map(depositRequest, TransactionRequest.class));
//        depositResponse.setMessage("deposit successful");
//        return depositResponse;
//    }

    private Customer getCustomerFor(DepositRequest depositRequest) throws UserNotFoundException {
        Customer customer = customerRepository.findById(depositRequest.getCustomerId())
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("Customer with id %d not found", depositRequest.getCustomerId())));
        return customer;
    }

    private static TransactionRequest createTransactionRequest(DepositRequest depositRequest, WalletDepositResponse response, Wallet customerWallet) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionStatus(String.valueOf(TransactionStatus.valueOf(response.getStatus())));
        transactionRequest.setTransactionAmount(depositRequest.getAmount());
        transactionRequest.setTransactionDate(LocalDate.now());
        transactionRequest.setSenderAccountNumber(depositRequest.getSederAccountNumber());
        transactionRequest.setReceiverAccountNumber(customerWallet.getAccountNumber());
        transactionRequest.setDescription(depositRequest.getDescription());
        return transactionRequest;
    }

    private static WalletDepositRequest createWalletDepositRequest(DepositRequest depositRequest, Wallet customerWallet) {
        WalletDepositRequest walletDepositRequest = new WalletDepositRequest();
        walletDepositRequest.setId(customerWallet.getId());
        walletDepositRequest.setAmount(depositRequest.getAmount());
        return walletDepositRequest;
    }
}
