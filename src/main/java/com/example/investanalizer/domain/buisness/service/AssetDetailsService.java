package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AssetDetailsService {
    Optional<AssetDetails> addAssetDetails(AssetDetails assetDetails);

    List<AssetDetails> getAllAssetDetails();

    Optional<AssetDetails> findAssetDetailsByTicker(String ticker);

    Optional<AssetDetails> findAssetDetailsById(Long id);

    Map<Asset, AssetDetails> getAssetDetailsFromAssetsList(List<Asset> assets);

    void deleteAssetDetailsById(Long id);
}
