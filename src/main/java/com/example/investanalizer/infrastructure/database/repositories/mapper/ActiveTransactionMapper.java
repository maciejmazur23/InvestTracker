package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActiveTransactionMapper {
    ActiveTransaction mapFromEntity(ActiveTransactionEntity entity);

    ActiveTransactionEntity mapToEntity(ActiveTransaction activeTransactionId);
}
