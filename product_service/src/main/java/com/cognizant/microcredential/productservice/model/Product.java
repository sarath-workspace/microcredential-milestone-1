package com.cognizant.microcredential.productservice.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Component
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    private int stock;

    private double price;

    private double offer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", offer=" + offer +
                '}';
    }
}
