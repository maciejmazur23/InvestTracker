package com.example.investanalizer.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"assetDetailsId"})
public class HistoricalAsset {
    Long assetId;
    AssetDetails assetDetailsId;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
    LocalDate historicalDate;
}
