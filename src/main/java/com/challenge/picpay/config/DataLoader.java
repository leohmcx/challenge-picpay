package com.challenge.picpay.config;

import com.challenge.picpay.entity.WalletType;
import com.challenge.picpay.repository.WalletTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import static java.util.Arrays.stream;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    @Override
    public void run(String... args) {
        stream(WalletType.Enum.values())
                .forEach(w -> {
                    final var walletType = w.get();
                    log.info("Setting up walletType: {}", walletType);
                    walletTypeRepository.save(walletType);
                });
    }
}
