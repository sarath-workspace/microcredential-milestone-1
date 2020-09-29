package com.cognizant.microcredential.orderservice.repository;

import com.cognizant.microcredential.orderservice.model.Order;
import com.cognizant.microcredential.orderservice.model.OrderKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, OrderKey> {

    public abstract List<Order> findByUserid(long userid);

}
