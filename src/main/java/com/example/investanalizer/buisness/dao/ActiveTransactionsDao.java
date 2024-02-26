package com.example.investanalizer.buisness.dao;

import com.example.investanalizer.domain.ActiveTransaction;

import java.util.List;
import java.util.Optional;

public interface ActiveTransactionsDao {
    List<ActiveTransaction> findByTicker(String ticker);

    ActiveTransaction saveActiveTransaction(ActiveTransaction activeTransactionId);

    Optional<ActiveTransaction> findById(Long activeTransactionId);
}
