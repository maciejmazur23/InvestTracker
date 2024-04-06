package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.objects.AssetDetails;
import com.example.investanalizer.infrastructure.database.entities.AssetDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetDetailsEntityMapper {
    AssetDetails mapFromEntity(AssetDetailsEntity entity);

    AssetDetailsEntity mapToEntity(AssetDetails assetDetails);
}
