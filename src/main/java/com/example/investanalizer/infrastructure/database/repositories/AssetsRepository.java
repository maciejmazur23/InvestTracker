package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.domain.buisness.dao.AssetsDao;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.infrastructure.database.repositories.jpa.AssetsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.AssetsEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AssetsRepository implements AssetsDao {
    private final AssetsJpaRepo assetsJpaRepo;
    private final AssetsEntityMapper assetsEntityMapper;

    @Override
    public Optional<Asset> findAssetsByAssetDetailsId(Long assetDetailsId) {
        return assetsJpaRepo.findById(assetDetailsId)
                .map(assetsEntityMapper::mapFromEntity);
    }

    @Override
    public List<Asset> findAllAssets() {
        return assetsJpaRepo.findAll().stream()
                .map(assetsEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Asset> saveAsset(Asset asset) {
        return Optional.of(assetsJpaRepo.save(assetsEntityMapper.mapToEntity(asset)))
                .map(assetsEntityMapper::mapFromEntity);
    }

}
