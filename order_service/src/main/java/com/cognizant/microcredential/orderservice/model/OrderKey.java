package com.cognizant.microcredential.orderservice.model;

import java.io.Serializable;

public class OrderKey implements Serializable {

    private long userid;

    private long productId;

    public OrderKey(){}

    public OrderKey(long userid, long productid) {
        this.userid = userid;
        this.productId = productid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderKey{" +
                "userid=" + userid +
                ", productId=" + productId +
                '}';
    }
}
