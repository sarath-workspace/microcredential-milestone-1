package com.cognizant.microcredential.productservice.controller;

import com.cognizant.microcredential.productservice.model.Product;
import com.cognizant.microcredential.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody @Valid Product product) {
        logger.info("The add Product service is called with the valuse {}", product);
        return productService.addProduct(product);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) {
        logger.info("The Update Product service has been invoked with product id {} and values {}", id, product);
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(value = "/delete")
    public void deleteProduct(@RequestParam(value = "id") Long productId) {
        logger.info("The Delete product service has been invoked with the product id {}", productId);
        productService.deleteProduct(productId);
    }

    @GetMapping(value = "/view/{productid}")
    public Product getProduct(
            @PathVariable("productid") long productid
    ) {
        logger.info("the Product info retrieval service has been invoked for product id {}", productid);
        return productService.viewProduct(productid);
    }
}
