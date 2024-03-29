package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.ActiveTransactionDTO;
import com.example.investanalizer.api.dto.mapper.ActiveTransactionDtoMapper;
import com.example.investanalizer.domain.buisness.managment.service.ActiveTransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ActiveTransactionsApi.ACTIVE_TRANSACTION_API)
public class ActiveTransactionsApi {
    public static final String ACTIVE_TRANSACTION_API = "/active-transactions";
    public static final String DELETE = "/delete/{id}";

    private final ActiveTransactionsService service;
    private final ActiveTransactionDtoMapper activeTransactionDtoMapper;

    @DeleteMapping(DELETE)
    ResponseEntity<ActiveTransactionDTO> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id)
                .map(activeTransactionDtoMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    ResponseEntity<List<ActiveTransactionDTO>> getActiveTransactions() {
        return ResponseEntity.ok(
                service.getActiveTransactions().stream()
                        .map(activeTransactionDtoMapper::mapToDTO)
                        .toList()
        );
    }
}
