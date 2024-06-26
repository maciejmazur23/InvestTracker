package com.example.investanalizer.infrastructure.database.repositories.jpa;

import com.example.investanalizer.infrastructure.database.entities.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsJpaRepo extends JpaRepository<AssetEntity, Long> {
}
