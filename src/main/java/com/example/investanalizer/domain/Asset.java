package com.example.investanalizer.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"assetDetailsId"})
public class Asset {
    Long assetId;
    AssetDetails assetDetailsId;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
}
