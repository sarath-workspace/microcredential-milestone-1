package com.cognizant.microcredential.userservice.config;

import com.cognizant.microcredential.userservice.exception.UserNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        HttpStatus status = null;
        if(ex instanceof UserNotFound)
            status = HttpStatus.NOT_FOUND;
        else if (ex instanceof SQLIntegrityConstraintViolationException)
            status = HttpStatus.CONFLICT;
        else
            status = HttpStatus.NOT_ACCEPTABLE;
        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), status);
    }

}
