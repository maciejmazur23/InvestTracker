package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.Transaction;

import java.util.List;
import java.util.Optional;

public interface AssetsService {
    List<Asset> findAllAssets();

    Optional<Asset> findByAssetDetailsId(Long assetDetailsId);

    Optional<Asset> saveAsset(Asset asset);

    void updateAssets(Transaction transaction);
}
