package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.Asset;
import com.example.investanalizer.infrastructure.database.entities.AssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetsMapper {
    Asset mapFromEntity(AssetEntity entity);
}
