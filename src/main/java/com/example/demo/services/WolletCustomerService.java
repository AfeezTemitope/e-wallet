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
import com.example.demo.dto.response.WalletDepositResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        //1. Retrieve the customer
        Customer customer = customerRepository.findById(depositRequest.getCustomerId())
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("Customer with id %d not found", depositRequest.getCustomerId())));
        //2. obtains the customer wallet
        Wallet customerWallet = customer.getWallet();
        WalletDepositRequest walletDepositRequest = createWalletDepositRequest(depositRequest, customerWallet);

        //3. Deposit into the customers wallet
        WalletDepositResponse response = walletService.deposit(walletDepositRequest);
        var depositResponse = modelMapper.map(response, DepositResponse.class);
        // TransactionRequest
        transactionsService.makeTransaction(modelMapper.map(depositRequest, TransactionRequest.class));
        depositResponse.setMessage("deposit successful");
        return depositResponse;
    }

    private static WalletDepositRequest createWalletDepositRequest(DepositRequest depositRequest, Wallet customerWallet) {
        WalletDepositRequest walletDepositRequest = new WalletDepositRequest();
        walletDepositRequest.setId(customerWallet.getId());
        walletDepositRequest.setAmount(depositRequest.getAmount());
        return walletDepositRequest;
    }
}
