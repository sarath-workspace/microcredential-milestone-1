package com.cognizant.microcredential.orderservice.service;

import com.cognizant.microcredential.orderservice.model.Checkout;
import com.cognizant.microcredential.orderservice.model.CheckoutKey;
import com.cognizant.microcredential.orderservice.repository.CheckoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("mysql")
public class CheckoutServiceMysqlImpl implements CheckoutService {

    private final Logger logger = LoggerFactory.getLogger(CheckoutServiceMysqlImpl.class);

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Override
    public void addChechout(List<Checkout> checkout) {
        logger.info("the add checkout Service is invoked with mysql implementation");
        checkoutRepository.saveAll(checkout);
    }

    @Override
    public void removeChechout(CheckoutKey key) {
        logger.info("the remove checkout Service is invoked with mysql implementation");
        List<Checkout> checkouts = checkoutRepository.findByUseridAndCode(key.getUserid(), key.getCode());
        checkoutRepository.deleteAll(checkouts);
    }

    @Override
    public List<Checkout> confirmation(CheckoutKey key) {
        logger.info("the checkout confirmation Service is invoked with mysql implementation");
        return checkoutRepository.findByUseridAndCode(key.getUserid(), key.getCode());
    }

}
