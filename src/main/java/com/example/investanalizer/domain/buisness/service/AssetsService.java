package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.Transaction;

import java.util.List;
import java.util.Optional;

public interface AssetsService {
    List<Asset> findAllAssets();

    Optional<Asset> findAssetsByAssetDetailsId(Long assetDetailsId);

    Optional<Asset> saveAsset(Asset asset);

    void updateAssets(Transaction transaction);
}
