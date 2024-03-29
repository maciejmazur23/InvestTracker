package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.domain.buisness.dao.HistoricalAssetsDao;
import com.example.investanalizer.domain.objects.HistoricalAsset;
import com.example.investanalizer.infrastructure.database.repositories.jpa.HistoricalAssetsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.HistoricalAssetsEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistoricalAssetsRepository implements HistoricalAssetsDao {
    private final HistoricalAssetsJpaRepo historicalAssetsJpaRepo;
    private final HistoricalAssetsEntityMapper historicalAssetsEntityMapper;

    @Override
    public void saveHistoricalAsset(HistoricalAsset historicalDate) {
        historicalAssetsJpaRepo.save(historicalAssetsEntityMapper.mapToEntity(historicalDate));
    }

    @Override
    public List<HistoricalAsset> findHistoricalAssets() {
        return historicalAssetsJpaRepo.findAll().stream()
                .map(historicalAssetsEntityMapper::mapFromEntity)
                .toList();
    }
}
