package com.lazarev.personalaccountservice.exception;

import com.lazarev.model.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            NoSuchClientException.class, NoSuchClientOrderException.class,
            NoSuchCargoException.class, NoSuchStationException.class,
            NoSuchDocumentException.class})
    public ResponseEntity<ErrorResponse> handleNoSuchClientException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                false, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}
