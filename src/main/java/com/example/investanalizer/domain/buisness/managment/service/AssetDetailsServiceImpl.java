package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.buisness.dao.AssetDetailsDao;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetDetailsServiceImpl implements AssetDetailsService {
    private final AssetDetailsDao assetDetailsDao;

    @Override
    public Optional<AssetDetails> addAssetDetails(AssetDetails assetDetails) {
        AssetDetails saved = assetDetailsDao.saveAssetDetails(assetDetails);
        if (saved == null) throw new RuntimeException("Asset Details exists!");
        return Optional.of(saved);
    }

    @Override
    public List<AssetDetails> getAllAssetDetails() {
        return assetDetailsDao.findAll();
    }

    @Override
    public Optional<AssetDetails> findByTicker(String ticker) {
        return assetDetailsDao.findByTicker(ticker);
    }

    @Override
    public Optional<AssetDetails> findById(Long id) {
        return assetDetailsDao.findById(id);
    }

    @Override
    public List<AssetDetails> getDetailsOfList(List<Asset> assets) {
        List<AssetDetails> assetDetails = new ArrayList<>();
        assets.forEach(asset -> assetDetails.add(assetDetailsDao.findById(asset.getAssetDetails().getAssetDetailsId())
                .orElseThrow())
        );
        return assetDetails;
    }

    @Override
    public Optional<AssetDetails> getByTicker(String ticker) {
        return assetDetailsDao.findByTicker(ticker);
    }
}
