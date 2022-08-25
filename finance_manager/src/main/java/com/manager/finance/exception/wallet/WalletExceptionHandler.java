package com.manager.finance.exception.wallet;

import com.manager.finance.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WalletExceptionHandler extends ResponseEntityExceptionHandler {
    private ApiError apiError;

    @ExceptionHandler(WalletNotFoundException.class)
    protected ResponseEntity<Object>
            handleWalletNotFoundException(WalletNotFoundException ex, WebRequest request) {
        apiError = new ApiError("There is no such wallet", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnableToDeleteWalletException.class)
    protected ResponseEntity<Object> handleUnableToDeleteWalletException(
            UnableToDeleteWalletException ex, WebRequest request) {
        apiError = new ApiError("You cannot delete this wallet", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TheSameWalletException.class)
    protected ResponseEntity<Object> handleTheSameWalletException(TheSameWalletException ex,
                                                                  WebRequest request) {
        apiError = new ApiError("You cannot have two wallets with the same name and currency",
                ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}

