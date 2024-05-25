package com.challenge.picpay.controller;

import com.challenge.picpay.controller.dto.CreateWalletDto;
import com.challenge.picpay.entity.Wallet;
import com.challenge.picpay.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/wallet")
    @ResponseStatus(code = CREATED)
    public Wallet create(@RequestBody @Valid CreateWalletDto dto) {
        return walletService.create(dto);
    }
}
