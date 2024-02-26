package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.buisness.dao.TransactionsDao;
import com.example.investanalizer.domain.Transaction;
import com.example.investanalizer.infrastructure.database.entities.TransactionEntity;
import com.example.investanalizer.infrastructure.database.repositories.jpa.TransactionsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionsRepository implements TransactionsDao {
    private final TransactionsJpaRepo transactionsJpaRepo;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction addTransaction(Transaction transaction) {
        TransactionEntity saved = transactionsJpaRepo.save(transactionMapper.mapToEntity(transaction));
        return transactionMapper.mapFromEntity(saved);
    }
}
