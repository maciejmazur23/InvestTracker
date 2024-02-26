package com.example.investanalizer.infrastructure.database.entities;

import com.example.investanalizer.domain.ASSET_TYPE;
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
    Long assetDetailsId;

    @Column(name = "asset_type")
    ASSET_TYPE assetType;

    @Column(name = "ticker")
    String ticker;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "api_key")
    String API_KEY;

}
