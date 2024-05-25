package com.challenge.picpay.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@RequiredArgsConstructor
public class WalletNotFoundException extends PicPayException {

    private final Long walletId;

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet not found");
        pb.setDetail("There is no wallet for this id " + walletId);

        return pb;
    }
}
