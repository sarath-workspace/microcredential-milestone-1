package com.cognizant.microcredential.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoStockException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public NoStockException(Long id, int availableqty, int reqqty) {
        super(String.format("Not Enough Item Found for checkout with productid -> %s requestedqty = %s but availbleqty -> %s", id, reqqty, availableqty));
    }
}
