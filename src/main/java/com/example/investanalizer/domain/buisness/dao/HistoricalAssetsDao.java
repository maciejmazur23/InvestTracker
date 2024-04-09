package com.example.investanalizer.domain.buisness.dao;

import com.example.investanalizer.domain.objects.HistoricalAsset;

import java.util.List;

public interface HistoricalAssetsDao {
    void saveHistoricalAsset(HistoricalAsset historicalDate);

    List<HistoricalAsset> findHistoricalAssets();
}
