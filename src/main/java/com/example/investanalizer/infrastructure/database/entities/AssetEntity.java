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
    private Long assetId;

    @Column(name = "asset_details_id")
    private Long assetDetailsId;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "course")
    private BigDecimal course;

    @Column(name = "totalValue")
    private BigDecimal totalValue;

}
