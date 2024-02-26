package com.example.investanalizer.domain;

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
public class Transaction {
    Long transactionId;
    Long activeTransactionId;
    TRANSACTION_TYPE transactionType;
    String ticker;
    BigDecimal quantity;
    BigDecimal course;
    BigDecimal totalValue;
    LocalDate dateOfTransaction;
}
