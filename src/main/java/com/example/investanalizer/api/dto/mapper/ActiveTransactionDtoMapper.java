package com.example.investanalizer.api.dto.mapper;

import com.example.investanalizer.api.dto.ActiveTransactionDTO;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActiveTransactionDtoMapper {
    ActiveTransaction mapFromDTO(ActiveTransactionDTO dto);

    ActiveTransactionDTO mapToDTO(ActiveTransaction activeTransactionId);
}
