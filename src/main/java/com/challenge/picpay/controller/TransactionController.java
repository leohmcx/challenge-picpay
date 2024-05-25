package com.challenge.picpay.controller;

import com.challenge.picpay.controller.dto.TransactionDto;
import com.challenge.picpay.entity.Transaction;
import com.challenge.picpay.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    private ResponseEntity<Transaction> makeTransfer(@RequestBody @Valid TransactionDto request) {
        return ResponseEntity.ok(service.makeTransfer(request));
    }
}
