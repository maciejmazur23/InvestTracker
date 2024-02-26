package com.example.investanalizer.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "assetId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ASSETS")
public class AssetEntity {

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

    @Column(name = "totalValue")
    BigDecimal totalValue;

}
