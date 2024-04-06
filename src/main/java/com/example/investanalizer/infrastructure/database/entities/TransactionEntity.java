package com.example.investanalizer.infrastructure.database.entities;

import com.example.investanalizer.domain.objects.enums.TRANSACTION_TYPE;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "transactionId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TRANSACTIONS")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactions_id")
    private Long transactionId;

    @Column(name = "transaction_type")
    private TRANSACTION_TYPE transactionType;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "course")
    private BigDecimal course;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "date_of_transaction")
    private LocalDate dateOfTransaction;
}
