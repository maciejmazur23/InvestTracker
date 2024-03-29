package com.example.investanalizer.domain.objects;

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
public class ActiveTransaction {
    Long activeTransactionId;
    String ticker;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
    LocalDate dateOfTransaction;
}
