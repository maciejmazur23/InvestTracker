package com.example.investanalizer.infrastructure.database.repositories.jpa;

import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveTransactionsJpaRepo extends JpaRepository<ActiveTransactionEntity, Long> {
    List<ActiveTransactionEntity> findByTicker(String ticker);
}