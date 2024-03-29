package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.buisness.dao.HistoricalAssetsDao;
import com.example.investanalizer.domain.buisness.managment.service.mapper.AssetToHistoricalMapper;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.HistoricalAsset;
import com.example.investanalizer.domain.objects.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoricalAssetsServiceImpl implements HistoricalAssetsService {
    private final HistoricalAssetsDao historicalAssetsDao;
    private final AssetToHistoricalMapper assetToHistoricalMapper;
    private final AssetsService assetsService;

    @Override
    public List<HistoricalAsset> getHistoricalAssets() {
        return historicalAssetsDao.findHistoricalAssets();
    }

    @Override
    @Transactional
    public void updateHistoricalAssets(Transaction transaction) {
        List<Asset> currentAssets = assetsService.findAllAssets();
        currentAssets.forEach(asset -> historicalAssetsDao.saveHistoricalAsset(
                assetToHistoricalMapper.mapAssetToHistoricalAsset(asset)
                        .withHistoricalDate(transaction.getDateOfTransaction())
        ));
    }
}
