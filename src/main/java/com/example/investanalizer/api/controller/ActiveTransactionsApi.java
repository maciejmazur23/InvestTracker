package com.example.investanalizer.api.controller;

import com.example.investanalizer.api.dto.ActiveTransactionDTO;
import com.example.investanalizer.api.dto.mapper.ActiveTransactionDtoMapper;
import com.example.investanalizer.domain.buisness.service.ActiveTransactionsService;
import com.example.investanalizer.domain.objects.ActiveTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ActiveTransactionsApi.ACTIVE_TRANSACTION_API)
public class ActiveTransactionsApi {
    public static final String ACTIVE_TRANSACTION_API = "/active-transactions";
    public static final String DELETE = "/delete/{id}";

    private final ActiveTransactionsService service;
    private final ActiveTransactionDtoMapper mapper;

    @DeleteMapping(DELETE)
    ResponseEntity<ActiveTransaction> deleteById(@PathVariable("id") Long id) {
        log.info("Id to delete: {}", id);
        Optional<ActiveTransaction> activeTransaction = service.deleteActiveTransactionById(id);
        return activeTransaction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    ResponseEntity<List<ActiveTransactionDTO>> getActiveTransactions() {
        return ResponseEntity.ok(
                service.getActiveTransactions().stream()
                        .map(mapper::mapToDTO)
                        .peek(dto -> log.info("ActiveTransaction: {}", dto))
                        .toList()
        );
    }
}
