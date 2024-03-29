package com.example.investanalizer.infrastructure.database.repositories.jpa;

import com.example.investanalizer.infrastructure.database.entities.AssetDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetDetailsJpaRepo extends JpaRepository<AssetDetailsEntity, Long> {
    Optional<AssetDetailsEntity> findByTicker(String ticker);
}
