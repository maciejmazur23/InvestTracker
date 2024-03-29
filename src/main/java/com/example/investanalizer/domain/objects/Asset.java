package com.example.investanalizer.domain.objects;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"assetDetails"})
public class Asset {
    Long assetId;
    AssetDetails assetDetails;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
}
