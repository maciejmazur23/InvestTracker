package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.objects.AssetDetails;
import com.example.investanalizer.infrastructure.database.entities.AssetDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetDetailsEntityMapper {

    @Mapping(target = "historicalAsset", ignore = true)
    @Mapping(target = "asset", ignore = true)
    AssetDetails mapFromEntity(AssetDetailsEntity entity);


    @Mapping(target = "historicalAsset", ignore = true)
    @Mapping(target = "asset", ignore = true)
    AssetDetailsEntity mapToEntity(AssetDetails assetDetails);
}
