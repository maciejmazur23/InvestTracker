package com.example.investanalizer.domain.buisness.dao;

import com.example.investanalizer.domain.objects.AssetDetails;

import java.util.List;
import java.util.Optional;

public interface AssetDetailsDao {
    Optional<AssetDetails> findAssetDetailsByTicker(String ticker);

    AssetDetails saveAssetDetails(AssetDetails assetDetails);

    List<AssetDetails> findAllAssetDetails();

    Optional<AssetDetails> findAssetDetailsById(Long id);

    void deleteAssetDetailsById(Long id);
}
