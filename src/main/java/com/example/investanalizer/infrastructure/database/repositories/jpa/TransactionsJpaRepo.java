package com.example.investanalizer.infrastructure.database.repositories.jpa;

import com.example.investanalizer.infrastructure.database.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsJpaRepo extends JpaRepository<TransactionEntity, Long> {
}
