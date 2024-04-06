package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.AssetDetailsDTO;
import com.example.investanalizer.api.dto.mapper.AssetDetailsDtoMapper;
import com.example.investanalizer.domain.buisness.service.AssetDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AssetDetailsApi.ASSET_DETAILS_API)
public class AssetDetailsApi {
    public static final String ASSET_DETAILS_API = "/asset-details";
    public static final String TICKER = "/{ticker}";
    public static final String DELETE = "/delete/{id}";

    private final AssetDetailsService service;
    private final AssetDetailsDtoMapper mapper;

    @PutMapping()
    ResponseEntity<AssetDetailsDTO> addAssetDetails(@RequestBody AssetDetailsDTO assetDetails) {
        log.info("AssetDetailsDTO: {}", assetDetails);
        return service.addAssetDetails(mapper.mapFromDTO(assetDetails))
                .map(mapper::mapToDTO)
                .stream()
                .peek(dto -> log.info("Saved AssetDetailsDTO: {}", dto))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping()
    ResponseEntity<List<AssetDetailsDTO>> getAll() {
        return ResponseEntity.ok(
                service.getAllAssetDetails().stream()
                        .map(mapper::mapToDTO)
                        .peek(dto -> log.info("AssetDetailsDTO: {}", dto))
                        .toList()
        );
    }

    @GetMapping(TICKER)
    ResponseEntity<AssetDetailsDTO> getByTicker(@Valid @PathVariable String ticker) {
        log.info("Ticker: {}", ticker);
        return service.findAssetDetailsByTicker(ticker)
                .map(mapper::mapToDTO)
                .stream()
                .peek(dto -> log.info("AssetDetailsDTO: {}", dto))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(DELETE)
    ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.info("Id to delete: {}", id);
        service.deleteAssetDetailsById(id);
        return ResponseEntity.ok().build();
    }
}
