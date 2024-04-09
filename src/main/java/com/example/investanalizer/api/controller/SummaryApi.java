package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.SummaryDTO;
import com.example.investanalizer.api.dto.mapper.SummaryMapper;
import com.example.investanalizer.domain.buisness.service.SummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(SummaryApi.SUMMARY_API)
public class SummaryApi {
    public static final String SUMMARY_API = "/summary";
    private final SummaryService service;
    private final SummaryMapper mapper;

    @GetMapping()
    ResponseEntity<SummaryDTO> getSummary() {
        SummaryDTO summary = mapper.mapToDTO(service.getSummary());
        log.info("Summary: {}", summary);
        return ResponseEntity.ok(summary);
    }
}
