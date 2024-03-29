package com.example.investanalizer.domain.buisness.dao;

import com.example.investanalizer.domain.objects.ActiveTransaction;

import java.util.List;
import java.util.Optional;

public interface ActiveTransactionsDao {
    List<ActiveTransaction> findByTicker(String ticker);

    ActiveTransaction saveActiveTransaction(ActiveTransaction activeTransactionId);

    Optional<ActiveTransaction> findById(Long activeTransactionId);

    ActiveTransaction deleteById(Long id);

    List<ActiveTransaction> findActiveTransactions();
}
