package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.AssetDetails;
import com.example.investanalizer.infrastructure.database.entities.AssetDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetDetailsMapper {
    AssetDetails mapFromEntity(AssetDetailsEntity entity);
}
