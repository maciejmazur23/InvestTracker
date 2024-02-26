package com.example.investanalizer.infrastructure.database.repositories.jpa;

import com.example.investanalizer.infrastructure.database.entities.AssetDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetDetailsJpaRepo extends JpaRepository<Long, AssetDetailsEntity> {
}
