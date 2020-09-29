package com.cognizant.microcredential.productservice.controller;

import com.cognizant.microcredential.productservice.common.TestUtil;
import com.cognizant.microcredential.productservice.model.Product;
import com.cognizant.microcredential.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void addProduct() throws Exception{
        Product productToAdd = new Product();

        productToAdd.setName("apple");
        productToAdd.setStock(20);
        productToAdd.setOffer(12.5);
        productToAdd.setPrice(15.6);

        Product addedProduct = new Product();

        addedProduct.setId(1);
        addedProduct.setName("apple");
        addedProduct.setStock(20);
        addedProduct.setOffer(12.5);
        addedProduct.setPrice(15.6);

        when(productService.addProduct(any(Product.class))).thenReturn(addedProduct);

        mvc.perform(post("/api/product/add")
                .content(TestUtil.convertObjectToJsonBytes(productToAdd))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(productToAdd.getName())))
                .andExpect(jsonPath("$.stock", is(productToAdd.getStock())))
                .andExpect(jsonPath("$.offer", is(productToAdd.getOffer())))
                .andExpect(jsonPath("$.price", is(productToAdd.getPrice())));
    }

    @Test
    void updateProduct() throws Exception {
        Product productToUpdate = new Product();

        productToUpdate.setName("apple");
        productToUpdate.setStock(20);
        productToUpdate.setOffer(12.5);
        productToUpdate.setPrice(15.6);

        Product updatedProduct = new Product();

        updatedProduct.setId(1);
        updatedProduct.setName("apple");
        updatedProduct.setStock(20);
        updatedProduct.setOffer(12.5);
        updatedProduct.setPrice(15.6);

        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(productToUpdate);

        mvc.perform(put("/api/product/update/1")
                .content(TestUtil.convertObjectToJsonBytes(updatedProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(productToUpdate.getName())))
                .andExpect(jsonPath("$.stock", is(productToUpdate.getStock())))
                .andExpect(jsonPath("$.offer", is(productToUpdate.getOffer())))
                .andExpect(jsonPath("$.price", is(productToUpdate.getPrice())));
    }

    @Test
    void deleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mvc.perform(delete("/api/product/delete")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getProduct() throws Exception {
        Product productDetails = new Product();

        productDetails.setId(1);
        productDetails.setName("apple");
        productDetails.setStock(20);
        productDetails.setOffer(12.5);
        productDetails.setPrice(15.6);

        when(productService.viewProduct(1L)).thenReturn(productDetails);

        mvc.perform(get("/api/product/view/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(productDetails.getName())))
                .andExpect(jsonPath("$.stock", is(productDetails.getStock())))
                .andExpect(jsonPath("$.offer", is(productDetails.getOffer())))
                .andExpect(jsonPath("$.price", is(productDetails.getPrice())));
    }
}