package com.example.investanalizer.api.dto.mapper;

import com.example.investanalizer.api.dto.AssetDetailsDTO;
import com.example.investanalizer.domain.objects.AssetDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetDetailsDtoMapper {

    @Mapping(target = "historicalAsset", ignore = true)
    @Mapping(target = "asset", ignore = true)
    AssetDetails mapFromAssetDetailsDTO(AssetDetailsDTO dto);

    @Mapping(target = "historicalAsset", ignore = true)
    @Mapping(target = "asset", ignore = true)
    AssetDetailsDTO mapToAssetDetailsDTO(AssetDetails assetDetails);
}
