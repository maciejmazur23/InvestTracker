package com.example.investanalizer.api.dto;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"assetDetails"})
public class AssetDTO {
    Long assetId;
    AssetDetailsDTO assetDetails;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
}
