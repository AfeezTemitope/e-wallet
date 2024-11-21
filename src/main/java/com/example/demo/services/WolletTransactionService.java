package com.example.demo.services;

import com.example.demo.data.models.Transactions;
import com.example.demo.data.repository.TransactionsRepository;
import com.example.demo.dto.request.TransactionRequest;
import com.example.demo.dto.response.TransactionResponse;
import com.example.demo.dto.response.TransactionStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.example.demo.dto.response.TransactionStatus.SUCCESS;

@Service
@AllArgsConstructor
public class WolletTransactionService implements TransactionService{

    private final ModelMapper modelMapper;
    private final TransactionsRepository transactionsRepository;

//    public WolletTransactionService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    @Override

    public TransactionResponse makeTransaction(TransactionRequest transactionRequest) {
        Transactions transactions = modelMapper.map(transactionRequest, Transactions.class);
        transactions = transactionsRepository.save(transactions);

        TransactionResponse transactionResponse = modelMapper.map(transactions, TransactionResponse.class);
        transactionResponse.setStatus(SUCCESS.toString());

        return transactionResponse;
    }

    @Override
    public TransactionResponse getTransactionBy(Long transactionId) {
        return modelMapper.map(transactionsRepository.findById(transactionId)
                .orElseThrow(()-> new RuntimeException(
                        "Transaction not found")), TransactionResponse.class);
    }

    public static void main(String[] args) {
        Transactions t1 = new Transactions();
        t1.setStatus(TransactionStatus.PENDING);
        ModelMapper modelMapper = new ModelMapper();
        System.out.println(modelMapper.map(t1, Transactions.class));

    }
}
