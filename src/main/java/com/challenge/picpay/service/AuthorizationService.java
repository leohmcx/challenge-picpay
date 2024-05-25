package com.challenge.picpay.service;

import com.challenge.picpay.client.AuthorizationClient;
import com.challenge.picpay.client.dto.AuthorizationResponse;
import com.challenge.picpay.entity.Transaction;
import com.challenge.picpay.exception.PicPayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient client;

    public boolean isAuthorized(Transaction transaction) {
        return ofNullable(client.isAuthorized())
                .filter(a -> isFalse(a.getStatusCode().isError()))
                .map(ResponseEntity::getBody)
                .map(AuthorizationResponse::authorized)
                .orElseThrow(PicPayException::new);
    }
}
