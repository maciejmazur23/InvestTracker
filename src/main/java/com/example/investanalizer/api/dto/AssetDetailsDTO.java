package com.example.investanalizer.api.dto;

import com.example.investanalizer.domain.objects.enums.ASSET_TYPE;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = {"ticker"})
public class AssetDetailsDTO {
    Long assetDetailsId;

    @NotNull
    ASSET_TYPE assetType;

    @NotNull @Length(min = 2, max = 10)
    String ticker;

    @NotNull
    String fullName;

    @NotNull @Length(min = 2, max = 8)
    String apiKey;
}
