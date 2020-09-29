package com.cognizant.microcredential.productservice.service;


import com.cognizant.microcredential.productservice.model.Product;

public interface ProductService {

    public abstract Product addProduct(Product product);

    public abstract Product updateProduct(Long id, Product product);

    public abstract void deleteProduct(Long productId);

    public abstract Product viewProduct(long productId);

}
