package com.challenge.picpay.client;

import com.challenge.picpay.entity.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @GetMapping
    ResponseEntity<Void> sendNotification(@RequestBody Transaction transaction);
}
