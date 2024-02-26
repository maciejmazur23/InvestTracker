package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.buisness.dao.ActiveTransactionsDao;
import com.example.investanalizer.domain.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.repositories.jpa.ActiveTransactionsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.ActiveTransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ActiveTransactionsRepository implements ActiveTransactionsDao {
    private final ActiveTransactionsJpaRepo activeTransactionsJpaRepo;
    private final ActiveTransactionMapper activeTransactionMapper;

    @Override
    public List<ActiveTransaction> findByTicker(String ticker) {
        return activeTransactionsJpaRepo.findByTicker(ticker).stream()
                .map(activeTransactionMapper::mapFromEntity)
                .toList();
    }

    @Override
    public ActiveTransaction saveActiveTransaction(ActiveTransaction activeTransactionId) {
        return activeTransactionMapper.mapFromEntity(activeTransactionsJpaRepo.save(
                activeTransactionMapper.mapToEntity(activeTransactionId)
        ));
    }

    @Override
    public Optional<ActiveTransaction> findById(Long id) {
        return activeTransactionsJpaRepo.findById(id)
                .map(activeTransactionMapper::mapFromEntity);
    }
}
