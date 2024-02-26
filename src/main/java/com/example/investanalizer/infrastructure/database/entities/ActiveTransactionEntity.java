package com.example.investanalizer.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "activeTransactionId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ACTIVE_TRANSACTIONS")
public class ActiveTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "active_transaction_id")
    Long activeTransactionId;

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
