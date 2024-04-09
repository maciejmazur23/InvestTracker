package com.example.investanalizer.domain.objects;

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
    Long assetDetailsId;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
    LocalDate historicalDate;
}
