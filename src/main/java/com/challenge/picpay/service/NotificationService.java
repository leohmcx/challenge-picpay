package com.challenge.picpay.service;

import com.challenge.picpay.client.NotificationClient;
import com.challenge.picpay.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationClient client;

    public void send(final Transaction transaction) {
        try {
            log.info("Sending notification for transaction: {}", transaction);

            ofNullable(client.sendNotification(transaction))
                    .map(ResponseEntity::getStatusCode)
                    .filter(HttpStatusCode::isError)
                    .ifPresent(c -> log.error("Error while sending notification "));
        } catch (Exception e) {
            log.error("Error while sending notification ", e);
        }
    }
}
