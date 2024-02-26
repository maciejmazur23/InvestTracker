package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.HistoricalAsset;
import com.example.investanalizer.infrastructure.database.entities.HistoricalAssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoricalAssetsMapper {
    HistoricalAsset mapFromEntity(HistoricalAssetEntity entity);
}
