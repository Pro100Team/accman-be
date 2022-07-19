package com.exadel.finance.manager.exception;

import java.sql.SQLException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerInternalServerException {
    @ExceptionHandler({IllegalStateException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse
    handleIllegalStateException(RuntimeException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse
    handleSqlException(RuntimeException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unable to process database request. Such object already exists");
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse
    handleObjectNotFoundException(RuntimeException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "There is no such object in the database");
    }
}
