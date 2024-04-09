package com.example.investanalizer.infrastructure.database.entities;

import com.example.investanalizer.domain.objects.enums.ASSET_TYPE;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "assetDetailsId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ASSET_DETAILS")
public class AssetDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_details_id")
    private Long assetDetailsId;

    @Column(name = "asset_type")
    private ASSET_TYPE assetType;

    @Column(name = "ticker", unique = true)
    private String ticker;

    @Column(name = "full_name", unique = true)
    private String fullName;

    @Column(name = "api_key", unique = true)
    private String apiKey;
}
