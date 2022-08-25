package com.manager.finance.exception.category;

import com.manager.finance.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CategoryExceptionHandler extends ResponseEntityExceptionHandler {

    private ApiError apiError;

    @ExceptionHandler(CategoryNotFoundException.class)
    protected ResponseEntity<Object>
            handleCategoryNotFoundException(CategoryNotFoundException ex) {
        apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryUsedException.class)
    protected ResponseEntity<Object>
            handleCategoryUsedException(CategoryUsedException ex) {
        apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
