package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.AssetDTO;
import com.example.investanalizer.api.dto.mapper.AssetsDtoMapper;
import com.example.investanalizer.domain.buisness.managment.service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AssetsApi.ASSETS_API)
public class AssetsApi {
    public static final String ASSETS_API = "/assets";
    private final AssetsService service;
    private final AssetsDtoMapper assetsDtoMapper;

    @GetMapping()
    ResponseEntity<List<AssetDTO>> getAssets() {
        return ResponseEntity.ok(
                service.findAllAssets().stream()
                        .map(assetsDtoMapper::mapToDTO)
                        .toList()
        );
    }
}
