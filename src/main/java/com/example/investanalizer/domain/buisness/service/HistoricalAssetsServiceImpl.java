package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.buisness.dao.HistoricalAssetsDao;
import com.example.investanalizer.domain.buisness.service.mapper.AssetToHistoricalMapper;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.HistoricalAsset;
import com.example.investanalizer.domain.objects.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info("currentAssets: {}", currentAssets);
        try {
            currentAssets.forEach(asset -> historicalAssetsDao.saveHistoricalAsset(
                    assetToHistoricalMapper.mapAssetToHistoricalAsset(asset)
                            .withHistoricalDate(transaction.getDateOfTransaction())
            ));
        }catch (Exception e){
            log.error("Error while save historical asset: {}", e.getMessage());
        }
    }
}
