package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.buisness.dao.AssetDetailsDao;
import com.example.investanalizer.infrastructure.database.repositories.jpa.AssetDetailsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.AssetDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AssetDetailsRepository implements AssetDetailsDao {
    private final AssetDetailsJpaRepo assetDetailsJpaRepo;
    private final AssetDetailsMapper assetDetailsMapper;
}
