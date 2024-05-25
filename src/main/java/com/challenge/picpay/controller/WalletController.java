package com.challenge.picpay.controller;

import com.challenge.picpay.controller.dto.CreateWalletDto;
import com.challenge.picpay.entity.Wallet;
import com.challenge.picpay.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> create(@RequestBody @Valid CreateWalletDto dto) {
        return ResponseEntity.ok(walletService.create(dto));
    }
}
