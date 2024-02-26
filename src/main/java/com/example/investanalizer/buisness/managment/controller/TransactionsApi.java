package com.example.investanalizer.buisness.managment.controller;

import com.example.investanalizer.buisness.managment.service.TransactionsService;
import com.example.investanalizer.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TransactionsApi {
    private final TransactionsService service;

    @PostMapping("/addTransaction")
    ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Optional<Transaction> savedTransaction = service.addTransaction(transaction);
        return savedTransaction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
