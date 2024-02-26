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
    Long assetId;

    @Column(name = "asset_details_id", unique = true)
    AssetDetailsEntity assetDetailsId;

    @Column(name = "quantity")
    BigDecimal quantity;

    @Column(name = "course")
    BigDecimal course;

    @Column(name = "total_value")
    BigDecimal totalValue;

    @Column(name = "historical_date")
    LocalDate historicalDate;

}
