package com.cognizant.microcredential.orderservice.service;

import com.cognizant.microcredential.orderservice.exception.OrderNotFound;
import com.cognizant.microcredential.orderservice.model.CheckoutRequest;
import com.cognizant.microcredential.orderservice.model.Order;
import com.cognizant.microcredential.orderservice.model.OrderKey;
import com.cognizant.microcredential.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("mysql")
public class OrderServiceMysqlImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceMysqlImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        logger.info("the add order Service is invoked with mysql implementation");
        return orderRepository.save(order);
    }

    @Override
    public Order updateProduct(OrderKey key, Order order) {
        logger.info("the update order Service is invoked with mysql implementation");
        Order orderToUpdate = orderRepository.findById(key).orElseThrow(() -> new OrderNotFound(key.getProductId(), key.getUserid()));
        orderToUpdate.setQuantity(order.getQuantity());
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public void deleteProduct(OrderKey key) {
        logger.info("the delete order Service is invoked with mysql implementation");
        orderRepository.deleteById(key);
    }

    @Override
    public List<Order> getAllOrders(Long userid) {
        logger.info("the retrieval order Service is invoked with mysql implementation");
        return orderRepository.findByUserid(userid);
    }
}
