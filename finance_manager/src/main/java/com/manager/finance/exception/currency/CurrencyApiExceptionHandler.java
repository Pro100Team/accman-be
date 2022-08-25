package com.manager.finance.exception.currency;

import com.manager.finance.exception.ApiError;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CurrencyApiExceptionHandler extends ResponseEntityExceptionHandler {
    private ApiError apiError;

    @ExceptionHandler(FeignException.NotFound.class)
    protected ResponseEntity<Object> handleFeignNotFoundException(
            FeignException.NotFound ex, WebRequest request) {
        apiError = new ApiError("Feign not found", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.BadRequest.class)
    protected ResponseEntity<Object> handleFeignBadRequestException(
            FeignException.BadRequest ex, WebRequest request) {
        apiError = new ApiError("Bad request", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.GatewayTimeout.class)
    protected ResponseEntity<Object> handleFeignGatewayTimeoutException(
            FeignException.GatewayTimeout ex, WebRequest request) {
        apiError = new ApiError("Gateway timeout", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    protected ResponseEntity<Object> handleFeignGatewayServiceUnavailable(
            FeignException.ServiceUnavailable ex, WebRequest request) {
        apiError = new ApiError("Service unavailable", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
