package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;

import java.util.List;
import java.util.Optional;

public interface ActiveTransactionsService {
    Optional<ActiveTransaction> deleteActiveTransactionById(Long id);

    List<ActiveTransaction> findAllActiveTransactions();

    Optional<ActiveTransaction> saveActiveTransaction(ActiveTransaction activeTransaction);
}
