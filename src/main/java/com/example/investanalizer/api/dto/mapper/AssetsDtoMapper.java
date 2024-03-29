package com.example.investanalizer.api.dto.mapper;

import com.example.investanalizer.api.dto.AssetDTO;
import com.example.investanalizer.domain.objects.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssetsDtoMapper {
    Asset mapFromDTO(AssetDTO dto);

    AssetDTO mapToDTO(Asset asset);
}
