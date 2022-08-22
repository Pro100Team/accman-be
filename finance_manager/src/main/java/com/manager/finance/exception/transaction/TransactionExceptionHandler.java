package com.manager.finance.exception.transaction;

import com.manager.finance.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {
    private ApiError ApiError;

    @ExceptionHandler(TransactionNotFoundException.class)
    protected ResponseEntity<Object> handleTransactionNotFoundException(TransactionNotFoundException ex,
                                                                 WebRequest request) {
        ApiError = new ApiError("There is no such transaction", ex.getMessage());
        return new ResponseEntity<>(ApiError, HttpStatus.NOT_FOUND);
    }
}
