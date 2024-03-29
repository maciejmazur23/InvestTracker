package com.example.investanalizer.api.dto;

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
public class ActiveTransactionDTO {
    Long activeTransactionId;
    String ticker;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
    LocalDate dateOfTransaction;
}
