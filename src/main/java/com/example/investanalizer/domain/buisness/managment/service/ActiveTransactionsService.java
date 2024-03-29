package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.domain.objects.Transaction;

import java.util.List;
import java.util.Optional;

public interface ActiveTransactionsService {
    Optional<ActiveTransaction> deleteById(Long id);

    List<ActiveTransaction> getActiveTransactions();

    List<ActiveTransaction> findActiveTransactionByTicker(String ticker);
}
