package com.example.demo.data.models;

import com.example.demo.dto.response.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private String message;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionStatus status;
}
