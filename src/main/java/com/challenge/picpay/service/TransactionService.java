package com.challenge.picpay.service;

import com.challenge.picpay.controller.dto.TransactionDto;
import com.challenge.picpay.entity.Transaction;
import com.challenge.picpay.entity.Wallet;
import com.challenge.picpay.entity.WalletType;
import com.challenge.picpay.exception.InsufficientBalanceException;
import com.challenge.picpay.exception.TransferNotAllowedForWalletTypeMerchant;
import com.challenge.picpay.exception.TransferNotAuthorizedException;
import com.challenge.picpay.exception.WalletNotFoundException;
import com.challenge.picpay.repository.TransactionRepository;
import com.challenge.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.challenge.picpay.entity.WalletType.Enum.MERCHANT;
import static java.util.concurrent.CompletableFuture.runAsync;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WalletRepository walletRepository;
    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction transfer(final TransactionDto request) {
        final var sender = walletRepository.findById(request.payer())
                .orElseThrow(() -> new WalletNotFoundException(request.payer()));
        final var receiver = walletRepository.findById(request.payee())
                .orElseThrow(() -> new WalletNotFoundException(request.payee()));

        isTransactionValid(request, sender);

        sender.debit(request.value());
        receiver.credit(request.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);

        final var transaction = transactionRepository.save(Transaction.builder()
                .receiver(receiver)
                .sender(sender)
                .value(request.value())
                .build());

        runAsync(() -> notificationService.send(transaction));

        return transaction;
    }

    private void isTransactionValid(final TransactionDto request, final Wallet sender) {
        if (MERCHANT.getDescription().equalsIgnoreCase(sender.getWalletType().getDescription())) {
            throw new TransferNotAllowedForWalletTypeMerchant();
        }

        if (sender.getBalance().subtract(request.value()).doubleValue() < 0) {
            throw new InsufficientBalanceException();
        }

        if (isFalse(authorizationService.isAuthorized(request))) {
            throw new TransferNotAuthorizedException();
        }
    }
}
