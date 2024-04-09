package com.example.investanalizer.domain.buisness.dao;

import com.example.investanalizer.domain.objects.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetsDao {
    Optional<Asset> findAssetsByAssetDetailsId(Long assetDetailsId);

    List<Asset> findAllAssets();

    Optional<Asset> saveAsset(Asset asset);
}
