package com.example.investanalizer.api.dto.mapper;

import com.example.investanalizer.api.dto.SummaryDTO;
import com.example.investanalizer.domain.objects.Summary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SummaryMapper {
    Summary mapFromDTO(SummaryDTO dto);

    SummaryDTO mapToDTO(Summary transaction);
}
