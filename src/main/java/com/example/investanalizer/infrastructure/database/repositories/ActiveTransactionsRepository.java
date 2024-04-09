package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;
import com.example.investanalizer.infrastructure.database.repositories.jpa.ActiveTransactionsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.ActiveTransactionEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ActiveTransactionsRepository implements ActiveTransactionsDao {
    private final ActiveTransactionsJpaRepo repository;
    private final ActiveTransactionEntityMapper mapper;

    @Override
    public List<ActiveTransaction> findActiveTransactionByTicker(String ticker) {
        return repository.findByTicker(ticker).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public ActiveTransaction saveActiveTransaction(ActiveTransaction activeTransactionId) {
        return mapper.mapFromEntity(repository.save(
                mapper.mapToEntity(activeTransactionId)
        ));
    }

    @Override
    public Optional<ActiveTransaction> findActiveTransactionById(Long id) {
        return repository.findById(id)
                .map(mapper::mapFromEntity);
    }

    @Override
    public void deleteActiveTransactionById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ActiveTransaction> findAllActiveTransactions() {
        return repository.findAll().stream()
                .map(mapper::mapFromEntity)
                .toList();
    }
}
