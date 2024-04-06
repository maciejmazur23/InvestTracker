package com.example.investanalizer.domain.objects;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"assetDetailsId"})
public class Asset {
    Long assetId;
    Long assetDetailsId;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
}
