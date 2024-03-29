package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.buisness.dao.AssetsDao;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;
import com.example.investanalizer.domain.objects.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetsServiceImpl implements AssetsService {
    private final AssetsDao assetsDao;
    private final AssetDetailsService assetDetailsService;

    @Override
    @Transactional
    public List<Asset> findAllAssets() {
        return assetsDao.findAll();
    }

    @Override
    public Optional<Asset> findByAssetDetailsId(Long assetDetailsId) {
        return assetsDao.findByAssetDetailsId(assetDetailsId);
    }

    @Override
    public Optional<Asset> saveAsset(Asset asset) {
        return assetsDao.saveAsset(asset);
    }

    @Override
    @Transactional
    public void updateAssets(Transaction transaction) {
        Optional<AssetDetails> optionalAssetDetails = assetDetailsService.findByTicker(transaction.getTicker());

        if (optionalAssetDetails.isEmpty()) {
            throw new RuntimeException("Chosen asset not exist!");
        }

        AssetDetails assetDetails = optionalAssetDetails.get();
        Optional<Asset> optionalAsset = findByAssetDetailsId(assetDetails.getAssetDetailsId());

        switch (transaction.getTransactionType()) {
            case SALE -> updateAssetsIfSale(transaction, optionalAsset);
            case PURCHASE -> updateAssetsIfPurchase(transaction, assetDetails, optionalAsset);
        }
    }

    private void updateAssetsIfPurchase(Transaction transaction, AssetDetails assetDetails, Optional<Asset> optionalAsset) {
        Optional<Asset> savedAsset;
        Asset asset;
        if (optionalAsset.isEmpty()) {
            asset = Asset.builder()
                    .assetDetails(assetDetails)
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
