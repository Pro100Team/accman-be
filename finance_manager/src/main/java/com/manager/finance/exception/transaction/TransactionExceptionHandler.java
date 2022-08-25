package com.manager.finance.exception.transaction;

import com.manager.finance.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {
    private ApiError apiError;

    @ExceptionHandler(TransactionNotFoundException.class)
    protected ResponseEntity<Object> handleTransactionNotFoundException(
            TransactionNotFoundException ex) {
        apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
