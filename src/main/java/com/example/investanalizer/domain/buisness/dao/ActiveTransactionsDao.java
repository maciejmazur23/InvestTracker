package com.example.investanalizer.domain.buisness.dao;

import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;

import java.util.List;
import java.util.Optional;

public interface ActiveTransactionsDao {
    List<ActiveTransaction> findActiveTransactionByTicker(String ticker);

    ActiveTransaction saveActiveTransaction(ActiveTransaction activeTransactionId);

    Optional<ActiveTransaction> findActiveTransactionById(Long activeTransactionId);

    void deleteActiveTransactionById(Long id);

    List<ActiveTransaction> findAllActiveTransactions();
}
