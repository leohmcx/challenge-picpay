package com.challenge.picpay.controller.dto;

import com.challenge.picpay.entity.Wallet;
import com.challenge.picpay.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDto(@NotBlank String fullName, @NotBlank String document, @NotBlank String email,
                              @NotBlank String password, @NotNull WalletType.Enum walletType) {
    public Wallet toWallet() {
        return Wallet.builder()
                .fullName(fullName)
                .document(document)
                .email(email)
                .password(password)
                .walletType(walletType.get())
                .build();
    }
}
