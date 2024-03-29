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
    private Long activeTransactionId;

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
