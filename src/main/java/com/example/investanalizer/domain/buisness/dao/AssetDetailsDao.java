package com.example.investanalizer.domain.buisness.dao;

import com.example.investanalizer.domain.objects.AssetDetails;

import java.util.List;
import java.util.Optional;

public interface AssetDetailsDao {
    Optional<AssetDetails> findByTicker(String ticker);

    AssetDetails saveAssetDetails(AssetDetails assetDetails);

    List<AssetDetails> findAll();

    Optional<AssetDetails> findById(Long id);
}
