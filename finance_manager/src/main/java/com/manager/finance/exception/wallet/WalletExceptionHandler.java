package com.manager.finance.exception.wallet;

import com.manager.finance.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WalletExceptionHandler extends ResponseEntityExceptionHandler {

    private ApiError apiError;

    @ExceptionHandler(WalletNotFoundException.class)
    protected ResponseEntity<Object>
                handleWalletNotFoundException(WalletNotFoundException ex) {
        apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnableToDeleteWalletException.class)
    protected ResponseEntity<Object> handleUnableToDeleteWalletException(
            UnableToDeleteWalletException ex) {
        apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TheSameWalletException.class)
    protected ResponseEntity<Object> handleTheSameWalletException(TheSameWalletException ex) {
        apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedError.class)
    protected ResponseEntity<Object>
            handleAccessDeniedError(AccessDeniedError ex) {
        apiError = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
}

