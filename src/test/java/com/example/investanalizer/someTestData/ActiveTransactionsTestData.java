package com.example.investanalizer.someTestData;

import com.example.investanalizer.api.dto.ActiveTransactionDTO;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import com.example.investanalizer.infrastructure.database.entities.ActiveTransactionEntity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ActiveTransactionsTestData {

    @NotNull
    public static List<ActiveTransactionDTO> getSomeActiveTransactionDTOS() {
        return List.of(
                ActiveTransactionDTO.builder()
                        .activeTransactionId(1L)
                        .dateOfTransaction(LocalDate.of(2024, 1, 10))
                        .course(BigDecimal.valueOf(4L))
                        .quantity(BigDecimal.valueOf(10L))
                        .ticker("USD")
                        .totalValue(BigDecimal.valueOf(40L))
                        .build(),
                ActiveTransactionDTO.builder()
                        .activeTransactionId(2L)
                        .dateOfTransaction(LocalDate.of(2024, 1, 21))
                        .course(BigDecimal.valueOf(100000L))
                        .quantity(BigDecimal.valueOf(0.01))
                        .ticker("BTC")
                        .totalValue(BigDecimal.valueOf(100L))
                        .build(),
                ActiveTransactionDTO.builder()
                        .activeTransactionId(3L)
                        .dateOfTransaction(LocalDate.of(2023, 12, 24))
                        .course(BigDecimal.valueOf(5000L))
                        .quantity(BigDecimal.valueOf(0.5))
                        .ticker("SPX")
                        .totalValue(BigDecimal.valueOf(2500L))
                        .build()
        );
    }

    @NotNull
    public static List<ActiveTransaction> getSomeActiveTransactions() {
        return List.of(
                ActiveTransaction.builder()
                        .activeTransactionId(1L)
                        .dateOfTransaction(LocalDate.of(2024, 1, 10))
                        .course(BigDecimal.valueOf(4L))
                        .quantity(BigDecimal.valueOf(10L))
                        .ticker("USD")
                        .totalValue(BigDecimal.valueOf(40L))
                        .build(),
                ActiveTransaction.builder()
                        .activeTransactionId(2L)
                        .dateOfTransaction(LocalDate.of(2024, 1, 21))
                        .course(BigDecimal.valueOf(100000L))
                        .quantity(BigDecimal.valueOf(0.01))
                        .ticker("BTC")
                        .totalValue(BigDecimal.valueOf(100L))
                        .build(),
                ActiveTransaction.builder()
                        .activeTransactionId(3L)
                        .dateOfTransaction(LocalDate.of(2023, 12, 24))
                        .course(BigDecimal.valueOf(5000L))
                        .quantity(BigDecimal.valueOf(0.5))
                        .ticker("SPX")
                        .totalValue(BigDecimal.valueOf(2500L))
                        .build()
        );
    }


    @NotNull
    public static List<ActiveTransactionEntity> getSomeActiveTransactionEntities() {
        return List.of(
                ActiveTransactionEntity.builder()
                        .activeTransactionId(1L)
                        .dateOfTransaction(LocalDate.of(2024, 1, 10))
                        .course(BigDecimal.valueOf(4L))
                        .quantity(BigDecimal.valueOf(10L))
                        .ticker("USD")
                        .totalValue(BigDecimal.valueOf(40L))
                        .build(),
                ActiveTransactionEntity.builder()
                        .activeTransactionId(2L)
                        .dateOfTransaction(LocalDate.of(2024, 1, 21))
                        .course(BigDecimal.valueOf(100000L))
                        .quantity(BigDecimal.valueOf(0.01))
                        .ticker("BTC")
                        .totalValue(BigDecimal.valueOf(100L))
                        .build(),
                ActiveTransactionEntity.builder()
                        .activeTransactionId(3L)
                        .dateOfTransaction(LocalDate.of(2023, 12, 24))
                        .course(BigDecimal.valueOf(5000L))
                        .quantity(BigDecimal.valueOf(0.5))
                        .ticker("SPX")
                        .totalValue(BigDecimal.valueOf(2500L))
                        .build()
        );
    }
}
