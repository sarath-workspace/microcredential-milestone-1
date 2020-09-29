package com.cognizant.microcredential.orderservice.service;

import com.cognizant.microcredential.orderservice.model.Checkout;
import com.cognizant.microcredential.orderservice.model.CheckoutKey;

import java.util.List;

public interface CheckoutService {

    public abstract void addChechout(List<Checkout> checkout);

    public abstract void removeChechout(CheckoutKey key);

    public abstract List<Checkout> confirmation(CheckoutKey key);

}
