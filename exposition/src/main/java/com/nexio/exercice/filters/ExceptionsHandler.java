package com.nexio.exercice.filters;

import com.nexio.exercice.exceptions.FunctionalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({FunctionalException.class})
    public ResponseEntity<ApiError> handleConstraintViolation(FunctionalException ex, WebRequest request) {

        ApiError apiError = new ApiError(ex.getCode(), ex.getMessage());

        //TODO : Map Error Codes to HTTP Statuses
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }


}