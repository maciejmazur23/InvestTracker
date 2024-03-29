package com.example.investanalizer.api.dto;

import com.example.investanalizer.domain.objects.enums.TRANSACTION_TYPE;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;

@With
@Value
@Builder
@ToString
public class TransactionDTO {
    Long transactionId;
    ActiveTransactionDTO activeTransaction;
    TRANSACTION_TYPE transactionType;
    @Size(min = 2, max = 6) String ticker;
    @NotNull BigDecimal quantity;
    @NotNull BigDecimal course;
    BigDecimal totalValue;
    LocalDate dateOfTransaction;
}
