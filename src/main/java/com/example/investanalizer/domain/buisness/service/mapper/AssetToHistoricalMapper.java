package com.example.investanalizer.domain.buisness.service.mapper;

import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.HistoricalAsset;
import org.springframework.stereotype.Service;

@Service
public class AssetToHistoricalMapper {

    public HistoricalAsset mapAssetToHistoricalAsset(Asset asset) {
        return HistoricalAsset.builder()
                .assetDetailsId(asset.getAssetDetailsId())
                .quantity(asset.getQuantity())
                .course(asset.getCourse())
                .totalValue(asset.getTotalValue())
                .build();
    }
}
