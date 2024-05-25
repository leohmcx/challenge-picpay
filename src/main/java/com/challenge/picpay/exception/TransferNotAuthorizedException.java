package com.challenge.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends PicPayException {
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer not authorized");
        pb.setDetail("Authorization service client not authorized this transaction");

        return pb;
    }
}
