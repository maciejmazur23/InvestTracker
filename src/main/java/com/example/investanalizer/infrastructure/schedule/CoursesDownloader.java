package com.example.investanalizer.infrastructure.schedule;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.forex.response.ForexResponse;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.example.investanalizer.domain.buisness.service.ActiveTransactionsService;
import com.example.investanalizer.domain.buisness.service.AssetDetailsService;
import com.example.investanalizer.domain.buisness.service.AssetsService;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.domain.objects.Asset;
import com.example.investanalizer.domain.objects.AssetDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoursesDownloader {
    private static final String API_KEY = "PVM56H8HE3UG3O9S";

    static {
        Config cfg = Config.builder()
                .key(API_KEY)
                .timeOut(10)
                .build();
        AlphaVantage.api().init(cfg);
    }

    private final AssetsService assetsService;
    private final AssetDetailsService assetDetailsService;
    private final ActiveTransactionsService activeTransactionsService;

    @Scheduled(cron = "0 0 17 * * *")
    void loadCourses() {
        log.info("loadCourses()");
        List<Asset> allAssets = assetsService.findAllAssets();
        log.info("allAssets: {}", allAssets);

        Map<Asset, AssetDetails> detailsFromList = assetDetailsService.getAssetDetailsFromAssetsList(allAssets);
        log.info("Asset-AssetDetails map: {}", detailsFromList);

        List<ActiveTransaction> allActiveTransactions = activeTransactionsService.findAllActiveTransactions();
        log.info("allActiveTransactions: {}", allActiveTransactions);

        BigDecimal usdPln = getForexCourse("USD", "PLN");

        Map<String, BigDecimal> courses = new HashMap<>();

        loadCoursesToMap(detailsFromList, courses);
        saveAssetsWithNewCourses(detailsFromList, usdPln, courses);
        saveActiveTransactionsWithNewCourses(allActiveTransactions, usdPln, courses);
    }

    private void saveActiveTransactionsWithNewCourses(
            List<ActiveTransaction> allActiveTransactions, BigDecimal usdPln, Map<String, BigDecimal> courses
    ) {
        allActiveTransactions.forEach(activeTransaction -> activeTransactionsService.saveActiveTransaction(activeTransaction
                .withCourse(courses.get(activeTransaction.getTicker()).multiply(usdPln))
                .withTotalValue(activeTransaction.getQuantity().multiply(courses.get(activeTransaction.getTicker())))
        ));
    }

    private void saveAssetsWithNewCourses(
            Map<Asset, AssetDetails> detailsFromList, BigDecimal usdPln, Map<String, BigDecimal> courses
    ) {
        detailsFromList.forEach((asset, assetDetails) -> assetsService.saveAsset(
                asset.withCourse(courses.get(assetDetails.getTicker()).multiply(usdPln))
        ));
    }

    private void loadCoursesToMap(Map<Asset, AssetDetails> detailsFromList, Map<String, BigDecimal> courses) {
        detailsFromList.forEach((asset, assetDetails) ->
                courses.put(assetDetails.getTicker(), getTimeSeriesCourse(assetDetails.getApiKey()))
        );
    }

    private BigDecimal getTimeSeriesCourse(String ticker) {
        QuoteResponse quote = AlphaVantage.api()
                .timeSeries()
                .quote()
                .forSymbol(ticker)
                .fetchSync();

        double price = quote.getPrice();
        log.info("{} : {}", ticker, price);

        return BigDecimal.valueOf(price);
    }

    private BigDecimal getForexCourse(String from, String to) {
        ForexResponse forexResponse = AlphaVantage.api()
                .forex()
                .daily()
                .fromSymbol(from)
                .toSymbol(to)
                .fetchSync();

        double course = forexResponse.getForexUnits()
                .get(0)
                .getClose();
        log.info("{}/{} : {}", from, to, course);

        return BigDecimal.valueOf(course);
    }
}
