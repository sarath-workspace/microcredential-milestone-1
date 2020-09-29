package com.cognizant.microcredential.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoItemsInCart extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Long userId;

    public NoItemsInCart(Long id) {
        super("No Item Found in the cart of the userid -> " + id);
        this.userId = id;
    }

    @Override
    public String getMessage() {
        return String.format("No Item Found in the cart of the userid -> &s", String.valueOf(userId));
    }
}
