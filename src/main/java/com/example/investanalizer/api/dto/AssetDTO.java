package com.example.investanalizer.api.dto;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"assetDetailsId"})
public class AssetDTO {
    Long assetId;
    Long assetDetailsId;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
}
