package com.cognizant.microcredential.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CheckoutOrderNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String code;

    public CheckoutOrderNotFound(Long id, String code) {
        super(String.format("No Checkout order Found for the uerid -> %s and the code -> %s", id, code));
        this.userId = id;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return String.format("No Checkout order Found for the uerid -> %s and the code -> %s", userId, code);
    }
}
