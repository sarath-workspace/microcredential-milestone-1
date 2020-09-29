package com.cognizant.microcredential.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Long userId;

    public UserNotFound(Long id) {
        super("User does not exist with id -> " + id);
        this.userId = id;
    }

    @Override
    public String getMessage() {
        return String.format("User with ID : %s doesn't exist", String.valueOf(userId));
    }

}
