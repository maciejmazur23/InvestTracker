package com.example.investanalizer.domain.buisness.managment.service;

import com.example.investanalizer.domain.objects.HistoricalAsset;
import com.example.investanalizer.domain.objects.Transaction;

import java.util.List;

public interface HistoricalAssetsService {
    List<HistoricalAsset> getHistoricalAssets();

    void updateHistoricalAssets(Transaction transaction);
}
