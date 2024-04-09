package com.example.investanalizer.domain.buisness.dao;

import com.example.investanalizer.domain.objects.Transaction;

import java.util.List;

public interface TransactionsDao {
    Transaction saveTransaction(Transaction transaction);

    List<Transaction> findAllTransactions();
}
