package com.cognizant.microcredential.orderservice.model;

import java.io.Serializable;

public class CheckoutKey implements Serializable {

    private long userid;

    private String code;

    private long productId;

    public CheckoutKey () {}

    public CheckoutKey(long userid, String code, long productId) {
        this.userid = userid;
        this.code = code;
        this.productId = productId;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CheckoutKey{" +
                "userid=" + userid +
                ", code='" + code + '\'' +
                ", productId=" + productId +
                '}';
    }
}
