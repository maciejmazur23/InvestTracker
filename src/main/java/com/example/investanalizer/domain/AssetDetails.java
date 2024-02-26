package com.example.investanalizer.domain;

import lombok.*;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"ticker"})
public class AssetDetails {
    Long assetDetailsId;
    ASSET_TYPE assetType;
    String ticker;
    String fullName;
    String API_KEY;
}
