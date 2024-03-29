package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;

import java.util.List;
import java.util.Optional;

public interface AssetDetailsService {
    Optional<AssetDetails> addAssetDetails(AssetDetails assetDetails);

    List<AssetDetails> getAllAssetDetails();

    Optional<AssetDetails> findByTicker(String ticker);

    Optional<AssetDetails> findById(Long id);

    List<AssetDetails> getDetailsOfList(List<Asset> assets);

    Optional<AssetDetails> getByTicker(String ticker);
}
