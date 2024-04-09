package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.TransactionDTO;
import com.example.investanalizer.api.dto.mapper.TransactionDtoMapper;
import com.example.investanalizer.domain.buisness.service.TransactionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(TransactionsApi.TRANSACTIONS_API)
public class TransactionsApi {
    public static final String TRANSACTIONS_API = "/transactions";
    private final TransactionsService service;
    private final TransactionDtoMapper mapper;

    @PostMapping()
    ResponseEntity<TransactionDTO> addTransaction(@Valid @RequestBody TransactionDTO dto) {
        log.info("TransactionDTO: {}", dto);
        return service.addTransaction(mapper.mapFromDTO(dto))
                .stream()
                .peek(saved -> log.info("Saved transaction: {}", saved))
                .findFirst()
                .map(mapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    ResponseEntity<List<TransactionDTO>> getTransactions() {
        return ResponseEntity.ok(
                service.findAllTransactions().stream()
                        .map(mapper::mapToDTO)
                        .peek(dto -> log.info("TransactionDTO: {}", dto))
                        .toList()
        );
    }

}
