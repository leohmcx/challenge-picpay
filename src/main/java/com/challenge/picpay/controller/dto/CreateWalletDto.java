package com.challenge.picpay.controller.dto;

import com.challenge.picpay.entity.Wallet;
import com.challenge.picpay.entity.WalletType;

public record CreateWalletDto(String fullName, String document, String email, String password, WalletType.Enum walletType) {
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
