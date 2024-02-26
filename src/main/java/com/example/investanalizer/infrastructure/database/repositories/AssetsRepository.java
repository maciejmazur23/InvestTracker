package com.example.investanalizer.infrastructure.database.repositories;

import com.example.investanalizer.buisness.dao.AssetsDao;
import com.example.investanalizer.infrastructure.database.repositories.jpa.AssetsJpaRepo;
import com.example.investanalizer.infrastructure.database.repositories.mapper.AssetsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AssetsRepository implements AssetsDao {
    private final AssetsJpaRepo assetsJpaRepo;
    private final AssetsMapper assetsMapper;
}
