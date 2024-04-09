package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.domain.buisness.dao.TransactionsDao;
import com.example.investanalizer.domain.objects.Transaction;
import com.example.investanalizer.infrastructure.database.entities.TransactionEntity;
import com.example.investanalizer.infrastructure.database.repositories.jpa.TransactionsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.TransactionEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionsRepository implements TransactionsDao {
    private final TransactionsJpaRepo transactionsJpaRepo;
    private final TransactionEntityMapper transactionEntityMapper;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        TransactionEntity saved = transactionsJpaRepo.save(transactionEntityMapper.mapToEntity(transaction));
        return transactionEntityMapper.mapFromEntity(saved);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionsJpaRepo.findAll().stream()
                .map(transactionEntityMapper::mapFromEntity)
                .toList();
    }
}
