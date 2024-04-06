package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.buisness.dao.AssetsDao;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;
import com.example.investanalizer.domain.objects.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetsServiceImpl implements AssetsService {
    private final AssetsDao assetsDao;
    private final AssetDetailsService assetDetailsService;

    @Override
    @Transactional
    public List<Asset> findAllAssets() {
        return assetsDao.findAllAssets();
    }

    @Override
    public Optional<Asset> findAssetsByAssetDetailsId(Long assetDetailsId) {
        return assetsDao.findAssetsByAssetDetailsId(assetDetailsId);
    }

    @Override
    public Optional<Asset> saveAsset(Asset asset) {
        return assetsDao.saveAsset(asset);
    }

    @Override
    @Transactional
    public void updateAssets(Transaction transaction) {
        try{
            Optional<AssetDetails> optionalAssetDetails = assetDetailsService.findAssetDetailsByTicker(transaction.getTicker());

            AssetDetails assetDetails = optionalAssetDetails.orElseThrow();
            Optional<Asset> optionalAsset = findAssetsByAssetDetailsId(assetDetails.getAssetDetailsId());

            switch (transaction.getTransactionType()) {
                case SALE -> updateAssetsIfSale(transaction, optionalAsset);
                case PURCHASE -> updateAssetsIfPurchase(transaction, assetDetails, optionalAsset);
            }
        }catch (Exception e){
            log.error("Chosen asset not exist: {}", e.getMessage());
            throw new RuntimeException("Chosen asset not exist!");
        }
    }

    private void updateAssetsIfPurchase(Transaction transaction, AssetDetails assetDetails, Optional<Asset> optionalAsset) {
        Optional<Asset> savedAsset;
        Asset asset;
        if (optionalAsset.isEmpty()) {
            asset = Asset.builder()
                    .assetDetailsId(assetDetails.getAssetDetailsId())
                    .quantity(transaction.getQuantity())
                    .course(transaction.getCourse())
                    .totalValue(transaction.getQuantity().multiply(transaction.getCourse()))
                    .build();
            savedAsset = saveAsset(asset);
        } else {
            asset = optionalAsset.get();
            savedAsset = saveAsset(
                    asset.withQuantity(asset.getQuantity().add(transaction.getQuantity()))
                            .withTotalValue(asset.getQuantity().multiply(asset.getCourse()))
            );
        }
        if (savedAsset.isEmpty()) throw new RuntimeException("Asset not saved!");
    }

    private void updateAssetsIfSale(Transaction transaction, Optional<Asset> optionalAsset) {
        Optional<Asset> savedAsset;
        if (optionalAsset.isEmpty()) throw new RuntimeException("Can not short assets in our system!");
        else {
            Asset asset = optionalAsset.get();
            if (asset.getQuantity().compareTo(transaction.getQuantity()) < 0)
                throw new RuntimeException("You sale more than you have!");
            else {
                savedAsset = saveAsset(asset.withQuantity(asset.getQuantity().subtract(transaction.getQuantity()))
                        .withTotalValue(asset.getQuantity().multiply(asset.getCourse()))
                );

                if (savedAsset.isEmpty())
                    throw new RuntimeException("Can not update assets!");
            }
        }
    }

}
