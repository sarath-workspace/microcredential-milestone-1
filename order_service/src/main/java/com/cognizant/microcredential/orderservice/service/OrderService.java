package com.cognizant.microcredential.orderservice.service;

import com.cognizant.microcredential.orderservice.model.Order;
import com.cognizant.microcredential.orderservice.model.OrderKey;

import java.util.List;

public interface OrderService {

    public abstract Order addOrder(Order order);

    public abstract Order updateProduct(OrderKey key, Order order);

    public abstract void deleteProduct(OrderKey key);

    public abstract List<Order> getAllOrders(Long userid);

}
