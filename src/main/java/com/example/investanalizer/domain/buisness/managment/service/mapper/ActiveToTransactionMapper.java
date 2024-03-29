package com.example.investanalizer.domain.buisness.managment.service.mapper;

import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.domain.objects.Transaction;
import org.springframework.stereotype.Service;

@Service
public class ActiveToTransactionMapper {

    public ActiveTransaction mapToActiveTransaction(Transaction transaction) {
        return ActiveTransaction.builder()
                .activeTransactionId(transaction.getActiveTransactionId())
                .ticker(transaction.getTicker())
                .quantity(transaction.getQuantity())
                .course(transaction.getCourse())
                .totalValue(transaction.getTotalValue())
                .dateOfTransaction(transaction.getDateOfTransaction())
                .build();
    }

    public Transaction mapToTransaction(ActiveTransaction transaction) {
        return Transaction.builder()
                .activeTransactionId(transaction.getActiveTransactionId())
                .ticker(transaction.getTicker())
                .quantity(transaction.getQuantity())
                .course(transaction.getCourse())
                .build();
    }
}
