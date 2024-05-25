package com.challenge.picpay.controller;

import com.challenge.picpay.exception.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicpayException(PicPayException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final var fieldErrors = e.getFieldErrors().stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        final var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Your request parameter is not valid");
        pb.setProperty("invalid-params", fieldErrors);

        return pb;
    }

    private record InvalidParam(String name, String reason){}
}
