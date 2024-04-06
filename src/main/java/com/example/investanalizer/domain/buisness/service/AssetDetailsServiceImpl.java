package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.buisness.dao.AssetDetailsDao;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetDetailsServiceImpl implements AssetDetailsService {
    private final AssetDetailsDao assetDetailsDao;

    @Override
    public Optional<AssetDetails> addAssetDetails(AssetDetails assetDetails) {
        try {
            assetDetailsDao.findAssetDetailsByTicker(assetDetails.getTicker()).ifPresent(a -> {
                throw new RuntimeException();
            });
            AssetDetails saved = assetDetailsDao.saveAssetDetails(assetDetails);
            return Optional.ofNullable(saved);
        } catch (Exception e) {
            throw new RuntimeException("Asset details exists!");
        }
    }

    @Override
    public List<AssetDetails> getAllAssetDetails() {
        return assetDetailsDao.findAllAssetDetails();
    }

    @Override
    public Optional<AssetDetails> findAssetDetailsByTicker(String ticker) {
        try {
            Optional<AssetDetails> byTicker = assetDetailsDao.findAssetDetailsByTicker(ticker);
            AssetDetails assetDetails = byTicker.orElseThrow();
            return Optional.of(assetDetails);
        } catch (Exception e) {
            throw new RuntimeException("Asset details not exist!");
        }
    }

    @Override
    public Optional<AssetDetails> findAssetDetailsById(Long id) {
        try {
            return assetDetailsDao.findAssetDetailsById(id);
        } catch (Exception e) {
            throw new RuntimeException("Asset details not exist!");
        }
    }

    @Override
    public Map<Asset, AssetDetails> getAssetDetailsFromAssetsList(List<Asset> assets) {
        Map<Asset, AssetDetails> resultMap = new HashMap<>();
        try {
            assets.forEach(asset -> resultMap.put(asset, assetDetailsDao.findAssetDetailsById(asset.getAssetDetailsId())
                    .orElseThrow())
            );
        } catch (Exception e) {
            log.error("Error while executing getDetailsOfList(): [%s]".formatted(e.getMessage()));
        }
        return resultMap;
    }

    @Override
    public void deleteAssetDetailsById(Long id) {
        try {
            int size = assetDetailsDao.findAllAssetDetails().size();
            assetDetailsDao.deleteAssetDetailsById(id);
            int sizeAfterDelete = assetDetailsDao.findAllAssetDetails().size();

            if (sizeAfterDelete < size) {
                log.info("Asset detail with id {} deleted correctly", id);
            } else throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException("Asset details not exist!");
        }
    }
}
