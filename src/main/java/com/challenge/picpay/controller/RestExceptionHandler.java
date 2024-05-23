package com.challenge.picpay.controller;

import com.challenge.picpay.exception.PicPayException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicpayException(PicPayException e) {
        return e.toProblemDetail();
    }
}
