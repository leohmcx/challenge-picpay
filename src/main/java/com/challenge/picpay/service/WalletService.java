package com.challenge.picpay.service;

import com.challenge.picpay.controller.dto.CreateWalletDto;
import com.challenge.picpay.entity.Wallet;
import com.challenge.picpay.exception.WalletDataAlreadyExistsException;
import com.challenge.picpay.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet create(final CreateWalletDto dto) {
        if (walletRepository.findByDocumentOrEmail(dto.document(), dto.email()).isPresent()) {
            throw new WalletDataAlreadyExistsException("Document or Email already exists!");
        }

        return walletRepository.save(dto.toWallet());
    }
}
