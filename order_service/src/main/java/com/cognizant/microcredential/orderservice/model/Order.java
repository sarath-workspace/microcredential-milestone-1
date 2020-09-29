 package com.cognizant.microcredential.orderservice.model;

import org.checkerframework.checker.index.qual.NonNegative;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "orders")
@IdClass(OrderKey.class)
public class Order {

    @Id
    private long userid;

    @Id
    private long productId;

    @NonNegative
    @Min(1)
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userid=" + userid +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
