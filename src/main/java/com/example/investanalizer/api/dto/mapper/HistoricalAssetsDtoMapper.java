package com.example.investanalizer.api.dto.mapper;

import com.example.investanalizer.api.dto.HistoricalAssetDTO;
import com.example.investanalizer.domain.objects.HistoricalAsset;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoricalAssetsDtoMapper {
    HistoricalAsset mapFromDTO(HistoricalAssetDTO dto);

    HistoricalAssetDTO mapToDTO(HistoricalAsset historicalDate);
}
