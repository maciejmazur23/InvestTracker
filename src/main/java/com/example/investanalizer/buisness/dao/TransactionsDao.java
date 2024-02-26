package com.example.investanalizer.buisness.dao;

import com.example.investanalizer.domain.Transaction;

import java.util.Optional;

public interface TransactionsDao {
    Transaction addTransaction(Transaction transaction);
}
