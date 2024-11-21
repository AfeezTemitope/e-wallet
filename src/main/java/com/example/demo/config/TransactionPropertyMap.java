package com.example.demo.config;

import org.modelmapper.PropertyMap;

public class TransactionPropertyMap <S,D>  extends PropertyMap<S, D> {
    @Override
    protected void configure() {
//        map().setTransactionType(source.getTransactionType());
    }
}
