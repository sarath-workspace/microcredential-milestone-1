package com.cognizant.microcredential.productservice.repository;

import com.cognizant.microcredential.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
