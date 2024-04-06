package com.example.investanalizer.api.dto.mapper;

import com.example.investanalizer.api.dto.TransactionDTO;
import com.example.investanalizer.domain.objects.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionDtoMapper {

    @Mapping(target = "transactionId", ignore = true)
    Transaction mapFromDTO(TransactionDTO dto);

    TransactionDTO mapToDTO(Transaction transaction);
}
