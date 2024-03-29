package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.TransactionDTO;
import com.example.investanalizer.api.dto.mapper.TransactionDtoMapper;
import com.example.investanalizer.domain.buisness.managment.service.TransactionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(TransactionsApi.TRANSACTIONS_API)
public class TransactionsApi {
    public static final String TRANSACTIONS_API = "/transactions";
    private final TransactionsService service;
    private final TransactionDtoMapper transactionDtoMapper;

    @PostMapping()
    ResponseEntity<TransactionDTO> addTransaction(@Valid @RequestBody TransactionDTO dto) {
        return service.addTransaction(transactionDtoMapper.mapFromDTO(dto))
                .map(transactionDtoMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    ResponseEntity<List<TransactionDTO>> getTransactions() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(transactionDtoMapper::mapToDTO)
                        .toList()
        );
    }

}
