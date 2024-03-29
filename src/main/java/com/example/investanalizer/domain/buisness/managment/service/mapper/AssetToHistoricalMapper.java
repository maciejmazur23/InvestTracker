package com.example.investanalizer.domain.buisness.managment.service.mapper;

import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.HistoricalAsset;
import org.springframework.stereotype.Service;

@Service
public class AssetToHistoricalMapper {

    public HistoricalAsset mapAssetToHistoricalAsset(Asset asset) {
        return HistoricalAsset.builder()
                .assetDetails(asset.getAssetDetails())
                .quantity(asset.getQuantity())
                .course(asset.getCourse())
                .totalValue(asset.getTotalValue())
                .build();
    }
}
