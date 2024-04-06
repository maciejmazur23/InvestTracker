package com.example.investanalizer.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "assetId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "HISTORICAL_ASSETS")
public class HistoricalAssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "asset_details_id")
    private Long assetDetailsId;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "course")
    private BigDecimal course;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "historical_date")
    private LocalDate historicalDate;
}
