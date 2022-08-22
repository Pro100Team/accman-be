package com.manager.finance.exception.user;

import com.manager.finance.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    private ApiError ApiError;

  protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
                                                              WebRequest request) {
     ApiError = new ApiError("There is no such user", ex.getMessage());
      return new ResponseEntity<>(ApiError, HttpStatus.NOT_FOUND);
   }
}