package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.domain.buisness.dao.AssetDetailsDao;
import com.example.investanalizer.domain.objects.AssetDetails;
import com.example.investanalizer.infrastructure.database.repositories.jpa.AssetDetailsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.AssetDetailsEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AssetDetailsRepository implements AssetDetailsDao {
    private final AssetDetailsJpaRepo assetDetailsJpaRepo;
    private final AssetDetailsEntityMapper assetDetailsEntityMapper;

    @Override
    public Optional<AssetDetails> findAssetDetailsByTicker(String ticker) {
        return assetDetailsJpaRepo.findByTicker(ticker)
                .map(assetDetailsEntityMapper::mapFromEntity);
    }

    @Override
    public AssetDetails saveAssetDetails(AssetDetails assetDetails) {
        return assetDetailsEntityMapper.mapFromEntity(
                assetDetailsJpaRepo.save(assetDetailsEntityMapper.mapToEntity(assetDetails))
        );
    }

    @Override
    public List<AssetDetails> findAllAssetDetails() {
        return assetDetailsJpaRepo.findAll().stream()
                .map(assetDetailsEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<AssetDetails> findAssetDetailsById(Long id) {
        return assetDetailsJpaRepo.findById(id).map(assetDetailsEntityMapper::mapFromEntity);
    }

    @Override
    public void deleteAssetDetailsById(Long id) {
        assetDetailsJpaRepo.deleteById(id);
    }
}
