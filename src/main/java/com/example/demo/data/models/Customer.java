package com.example.demo.data.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    private Long id;
    @OneToOne(cascade = PERSIST)
    private Wallet wallet;

}
