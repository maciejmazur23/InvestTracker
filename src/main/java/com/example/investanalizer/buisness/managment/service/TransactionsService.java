package com.example.investanalizer.buisness.managment.service;

import com.example.investanalizer.domain.Transaction;

import java.util.Optional;

public interface TransactionsService {
    Optional<Transaction> addTransaction(Transaction transaction);
}
