package com.cognizant.microcredential.orderservice.repository;

import com.cognizant.microcredential.orderservice.model.Checkout;
import com.cognizant.microcredential.orderservice.model.CheckoutKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckoutRepository extends CrudRepository<Checkout, CheckoutKey> {

    public abstract List<Checkout> findByUseridAndCode(long userid, String code);
}
