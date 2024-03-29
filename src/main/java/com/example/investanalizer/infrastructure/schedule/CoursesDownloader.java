package com.example.investanalizer.infrastructure.schedule;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.example.investanalizer.domain.buisness.managment.service.AssetDetailsService;
import com.example.investanalizer.domain.buisness.managment.service.AssetsService;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CoursesDownloader {
    private static final String API_KEY = "PVM56H8HE3UG3O9S";
    private final AssetsService assetsService;
    private final AssetDetailsService assetDetailsService;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.DAYS)
    void loadCourses() {
        List<Asset> allAssets = assetsService.findAllAssets();
        List<AssetDetails> detailsOfList = assetDetailsService.getDetailsOfList(allAssets);

        Map<AssetDetails, BigDecimal> courses = new HashMap<>();

        detailsOfList.forEach(assetDetails ->
                courses.put(assetDetails, getCourses(assetDetails.getAPI_KEY()))
        );
        allAssets.forEach(asset ->
                assetsService.saveAsset(asset.withCourse(courses.get(asset.getAssetDetails())))
        );
    }

    private BigDecimal getCourses(String ticker) {
        Config cfg = Config.builder()
                .key(API_KEY)
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

        QuoteResponse quote = AlphaVantage.api()
                .timeSeries()
                .quote()
                .forSymbol(ticker)
                .fetchSync();

        return BigDecimal.valueOf(quote.getPrice());
    }
}
