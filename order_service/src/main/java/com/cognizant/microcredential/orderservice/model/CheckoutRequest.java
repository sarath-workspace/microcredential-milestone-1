package com.cognizant.microcredential.orderservice.model;

import java.util.List;

public class CheckoutRequest {

    private long userid;

    private List<Long> products;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CheckoutRequest{" +
                "userid=" + userid +
                ", products=" + products +
                '}';
    }
}
