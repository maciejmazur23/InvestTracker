package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.buisness.dao.HistoricalAssetsDao;
import com.example.investanalizer.infrastructure.database.repositories.jpa.HistoricalAssetsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.HistoricalAssetsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HistoricalAssetsRepository implements HistoricalAssetsDao {
    private final HistoricalAssetsJpaRepo historicalAssetsJpaRepo;
    private final HistoricalAssetsMapper historicalAssetsMapper;
}
