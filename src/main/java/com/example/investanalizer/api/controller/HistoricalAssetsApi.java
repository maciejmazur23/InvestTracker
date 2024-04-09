package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.HistoricalAssetDTO;
import com.example.investanalizer.api.dto.mapper.HistoricalAssetsDtoMapper;
import com.example.investanalizer.domain.buisness.service.HistoricalAssetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(HistoricalAssetsApi.HISTORICAL_ASSETS_API)
public class HistoricalAssetsApi {
    public static final String HISTORICAL_ASSETS_API = "/historical-assets";
    private final HistoricalAssetsService service;
    private final HistoricalAssetsDtoMapper mapper;

    @GetMapping()
    ResponseEntity<List<HistoricalAssetDTO>> getHistoricalAssets() {
        return ResponseEntity.ok(
                service.getHistoricalAssets().stream()
                        .map(mapper::mapToDTO)
                        .peek(dto -> log.info("HistoricalAssetDTO: {}", dto))
                        .toList()
        );
    }
}
