package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.objects.HistoricalAsset;
import com.example.investanalizer.infrastructure.database.entities.HistoricalAssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoricalAssetsEntityMapper {
    HistoricalAsset mapFromEntity(HistoricalAssetEntity entity);

    HistoricalAssetEntity mapToEntity(HistoricalAsset historicalDate);
}
