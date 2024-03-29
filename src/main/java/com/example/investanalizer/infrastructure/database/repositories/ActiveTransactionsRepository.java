package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.domain.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.repositories.jpa.ActiveTransactionsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.ActiveTransactionEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ActiveTransactionsRepository implements ActiveTransactionsDao {
    private final ActiveTransactionsJpaRepo activeTransactionsJpaRepo;
    private final ActiveTransactionEntityMapper activeTransactionEntityMapper;

    @Override
    public List<ActiveTransaction> findByTicker(String ticker) {
        return activeTransactionsJpaRepo.findByTicker(ticker).stream()
                .map(activeTransactionEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public ActiveTransaction saveActiveTransaction(ActiveTransaction activeTransactionId) {
        return activeTransactionEntityMapper.mapFromEntity(activeTransactionsJpaRepo.save(
                activeTransactionEntityMapper.mapToEntity(activeTransactionId)
        ));
    }

    @Override
    public Optional<ActiveTransaction> findById(Long id) {
        return activeTransactionsJpaRepo.findById(id)
                .map(activeTransactionEntityMapper::mapFromEntity);
    }

    @Override
    public ActiveTransaction deleteById(Long id) {
        return activeTransactionEntityMapper.mapFromEntity(activeTransactionsJpaRepo.deleteByActiveTransactionId(id));
    }

    @Override
    public List<ActiveTransaction> findActiveTransactions() {
        return activeTransactionsJpaRepo.findAll().stream()
                .map(activeTransactionEntityMapper::mapFromEntity)
                .toList();
    }
}
