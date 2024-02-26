package com.example.investanalizer.infrastructure.database.entities;

import com.example.investanalizer.domain.TRANSACTION_TYPE;
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
    Long transactionId;

    @Column(name = "active_transaction_id")
    String activeTransactionId;

    @Column(name = "transaction_type")
    TRANSACTION_TYPE transactionType;

    @Column(name = "ticker")
    String ticker;

    @Column(name = "quantity")
    BigDecimal quantity;

    @Column(name = "course")
    BigDecimal course;

    @Column(name = "total_value")
    BigDecimal totalValue;

    @Column(name = "date_of_transaction")
    LocalDate dateOfTransaction;
}
