package com.example.investanalizer.infrastructure.database.repositories.mapper;

import com.example.investanalizer.domain.objects.Transaction;
import com.example.investanalizer.infrastructure.database.entities.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionEntityMapper {
    Transaction mapFromEntity(TransactionEntity entity);

    TransactionEntity mapToEntity(Transaction transaction);
}
