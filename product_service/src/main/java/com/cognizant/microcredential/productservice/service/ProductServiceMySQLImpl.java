package com.cognizant.microcredential.productservice.service;

import com.cognizant.microcredential.productservice.exception.ProductNotFound;
import com.cognizant.microcredential.productservice.model.Product;
import com.cognizant.microcredential.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(value = "mysql")
public class ProductServiceMySQLImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceMySQLImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        logger.info("the add product service mysql implementation has been invoked");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        logger.info("the update product service mysql implementation has been invoked");
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));

        productToUpdate.setName(product.getName());
        productToUpdate.setStock(product.getStock());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setOffer(product.getOffer());

        logger.info("the update for the product id {} and values {} is in progress...", id, product);
        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(Long productId) {
        logger.info("the delete product service mysql implementation has been invoked for the product id {}", productId);
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFound(productId));
        productRepository.deleteById(productId);
    }

    @Override
    public Product viewProduct(long productId) {
        logger.info("the view product service mysql implementation has been invoked");
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFound(productId));
    }
}
