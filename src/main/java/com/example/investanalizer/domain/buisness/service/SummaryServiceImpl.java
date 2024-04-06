package com.example.investanalizer.domain.buisness.service;

import com.example.investanalizer.domain.buisness.dao.AssetsDao;
import com.example.investanalizer.domain.buisness.dao.HistoricalAssetsDao;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.HistoricalAsset;
import com.example.investanalizer.domain.objects.Summary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final AssetsDao assetsDao;
    private final HistoricalAssetsDao historicalAssetsDao;

    @Override
    public Summary getSummary() {
        List<Asset> assets = assetsDao.findAllAssets();
        List<HistoricalAsset> lastHistoricalAssets = getLastHistoricalAssets();

        BigDecimal actualAssetsValue = assets.stream()
                .map(Asset::getTotalValue)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        BigDecimal lastHistoricalAssetsValue = lastHistoricalAssets.stream()
                .map(HistoricalAsset::getTotalValue)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return Summary.builder()
                .totalValue(actualAssetsValue)
                .increaseOfValue(actualAssetsValue.subtract(lastHistoricalAssetsValue))
                .percentageIncrease(actualAssetsValue.divide(lastHistoricalAssetsValue, 2, RoundingMode.HALF_UP))
                .build();
    }

    private List<HistoricalAsset> getLastHistoricalAssets() {
        return historicalAssetsDao.findHistoricalAssets().stream()
                .collect(Collectors.groupingBy(HistoricalAsset::getHistoricalDate))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }
}
