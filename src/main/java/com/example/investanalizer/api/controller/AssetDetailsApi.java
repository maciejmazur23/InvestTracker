package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.AssetDetailsDTO;
import com.example.investanalizer.api.dto.mapper.AssetDetailsDtoMapper;
import com.example.investanalizer.domain.buisness.managment.service.AssetDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AssetDetailsApi.ASSET_DETAILS_API)
public class AssetDetailsApi {
    public static final String ASSET_DETAILS_API = "/asset-details";
    public static final String TICKER = "/{ticker}";

    private final AssetDetailsService service;
    private final AssetDetailsDtoMapper assetDetailsDtoMapper;

    @PostMapping()
    ResponseEntity<AssetDetailsDTO> addAssetDetails(@RequestBody AssetDetailsDTO assetDetails) {
        return service.addAssetDetails(assetDetailsDtoMapper.mapFromAssetDetailsDTO(assetDetails))
                .map(assetDetailsDtoMapper::mapToAssetDetailsDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping()
    ResponseEntity<List<AssetDetailsDTO>> getAll() {
        return ResponseEntity.ok(
                service.getAllAssetDetails().stream()
                        .map(assetDetailsDtoMapper::mapToAssetDetailsDTO)
                        .toList()
        );
    }

    @GetMapping(TICKER)
    ResponseEntity<AssetDetailsDTO> getByTicker(@Valid @PathVariable String ticker) {
        return service.getByTicker(ticker)
                .map(assetDetailsDtoMapper::mapToAssetDetailsDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
