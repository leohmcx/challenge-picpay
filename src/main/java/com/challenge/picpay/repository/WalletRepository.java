package com.challenge.picpay.repository;

import com.challenge.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByDocumentOrEmail(String document, String email);
}
