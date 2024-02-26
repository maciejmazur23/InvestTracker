package com.example.investanalizer.buisness.managment.service;

import com.example.investanalizer.domain.ActiveTransaction;
import com.example.investanalizer.domain.Transaction;
import org.springframework.stereotype.Service;

@Service
public class ActiveToTransactionMapper {

    ActiveTransaction mapTransactionToActiveTransaction(Transaction transaction) {
        return ActiveTransaction.builder()
                .transactionId(transaction.getActiveTransactionId())
                .ticker(transaction.getTicker())
                .quantity(transaction.getQuantity())
                .course(transaction.getCourse())
                .totalValue(transaction.getTotalValue())
                .dateOfTransaction(transaction.getDateOfTransaction())
                .build();
    }
}
