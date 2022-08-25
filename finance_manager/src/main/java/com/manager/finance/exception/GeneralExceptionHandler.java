package com.manager.finance.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    private ApiError apiError;

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            @Nullable HttpHeaders headers, @NonNull HttpStatus status,
            @Nullable WebRequest request) {
        apiError = new ApiError("Malformed JSON Request", ex.getMessage());
        return new ResponseEntity<>(apiError, status);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @Nullable HttpHeaders headers, @NonNull HttpStatus status,
            @Nullable WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ApiError apiError = new ApiError("Method Argument Not Valid", ex.getMessage(), errors);
        return new ResponseEntity<>(apiError, status);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            @Nullable HttpHeaders headers, @NonNull HttpStatus status,
            @Nullable WebRequest request) {
        return new ResponseEntity<Object>(new ApiError("No Handler Found", ex.getMessage()),
                status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(String.format(
                "The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(),
                Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
        apiError.setDebugMessage(ex.getMessage());
        apiError.setStatusCode(BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }
}
