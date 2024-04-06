package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.objects.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionsService {
    Optional<Transaction> addTransaction(Transaction transaction);

    List<Transaction> findAllTransactions();
}
