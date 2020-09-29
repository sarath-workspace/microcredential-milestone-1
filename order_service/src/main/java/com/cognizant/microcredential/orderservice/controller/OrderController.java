package com.cognizant.microcredential.orderservice.controller;

import com.cognizant.microcredential.orderservice.exception.NoItemsInCart;
import com.cognizant.microcredential.orderservice.exception.NoStockException;
import com.cognizant.microcredential.orderservice.model.*;
import com.cognizant.microcredential.orderservice.service.CheckoutService;
import com.cognizant.microcredential.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Value("${microservice.user.view}")
    private String userViewRequest;

    @Value("${microservice.product.view}")
    private String productViewRequest;

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order) {
        logger.info("The Add order service has been invoked with the values :: {}", order);
        return orderService.addOrder(order);
    }

    @PutMapping("/update/{userid}/{productid}")
    public Order updateOrder(
            @PathVariable("userid") long userid,
            @PathVariable("productid") long productid,
            @RequestBody Order order
    ) {
        logger.info("The update order service has been invoked for the userid::{}, productid::{}, values:: {}", userid, productid, order);
        return orderService.updateProduct(new OrderKey(userid, productid), order);
    }

    @DeleteMapping("/delete/{userid}/{productid}")
    public void deleteOrder(
            @PathVariable("userid") long userid,
            @PathVariable("productid") long productid
    ) {
        logger.info("The delete order service has been invoked with the userid::{}, productid::{}",userid, productid);
        orderService.deleteProduct(new OrderKey(userid, productid));
    }

    @GetMapping("/view/{userid}")
    public CheckoutOrder getAllOrder(
            @PathVariable("userid") long userid
    ) {
        logger.info("The Order information retrieval request has been invoked with the userid::{}", userid);
        User user = webClient.build().get()
                .uri(String.format(userViewRequest, userid))
                .retrieve()
                .bodyToMono(User.class)
                .block();
        logger.info("the user details has been retrieved from {}", String.format(userViewRequest, userid));
        logger.info("the user information is {}", user);

        List<Product> products = orderService.getAllOrders(userid).stream()
                .map(order -> {
                    Product product = webClient.build()
                            .get().uri(String.format(productViewRequest, order.getProductId()))
                            .retrieve()
                            .bodyToMono(Product.class)
                            .block();
                    logger.info("the product info retrieval request has been invoked by {}", String.format(productViewRequest, order.getProductId()));
                    logger.info("the product details {}", product);
                    product.setPrice(product.getPrice() * order.getQuantity());
                    return product;
                })
                .collect(Collectors.toList());

        double netPrice = products.stream().mapToDouble(product -> product.getPrice()).sum();
        double discountPrice = products.stream().mapToDouble(product -> (product.getPrice() * product.getOffer()) / 100).sum();

        logger.info("the netprice::{}, discount::{}", netPrice, discountPrice);
        return new CheckoutOrder.Builder()
                .userid(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .orderItems(products)
                .netPrice(netPrice)
                .discount(discountPrice)
                .totalPrice(netPrice - discountPrice)
                .build();
    }

    @PutMapping("/checkout")
    public void checkout(@RequestBody CheckoutRequest checkoutRequest) {

        logger.info("the Checkout order service has been invoked with {}", checkoutRequest);
        Stream<Order> orders = orderService.getAllOrders(checkoutRequest.getUserid()).stream();

        String code = UUID.randomUUID().toString().substring(0, 8);

        if (Objects.nonNull(checkoutRequest.getProducts()) && !checkoutRequest.getProducts().isEmpty())
            orders = orders.filter(order -> checkoutRequest.getProducts().contains(order.getProductId()));
        List<Checkout> checkoutProducts = orders.map(order -> {
            Product product = webClient.build()
                    .get().uri(String.format(productViewRequest, order.getProductId()))
                    .retrieve()
                    .bodyToMono(Product.class)
                    .block();
            logger.info("the product info retrieval request has been invoked by {}", String.format(productViewRequest, order.getProductId()));
            logger.info("the product details {}", product);
            if (product.getStock() < order.getQuantity())
                throw new NoStockException(product.getId(), product.getStock(), order.getQuantity());

            return new Checkout.Builder()
                    .userid(checkoutRequest.getUserid())
                    .code(code)
                    .productId(product.getId())
                    .productName(product.getName())
                    .quantity(order.getQuantity())
                    .price(product.getPrice() * order.getQuantity())
                    .offer((product.getPrice() * order.getQuantity() * product.getOffer()) / 100)
                    .build();
        }).collect(Collectors.toList());

        checkoutService.addChechout(checkoutProducts);
        checkoutProducts.forEach(checkout -> orderService.deleteProduct(new OrderKey(checkout.getUserid(), checkout.getProductId())));
    }

    @GetMapping("/comfirm")
    public CheckoutOrder confirmOrder(
            @RequestBody CheckoutKey checkout
    ) {
        logger.info("the Order confirmation request has been invoked with {}", checkout);

        User user = webClient.build().get()
                .uri(String.format(userViewRequest, checkout.getUserid()))
                .retrieve()
                .bodyToMono(User.class)
                .block();

        logger.info("the user details has been retrieved from {}", String.format(userViewRequest, checkout.getUserid()));
        logger.info("the user information is {}", user);

        List<Checkout> checkoutProducts = checkoutService.confirmation(checkout);

        if (null == checkoutProducts || checkoutProducts.isEmpty())
            throw new NoItemsInCart(checkout.getUserid());

        List<Product> products = checkoutProducts.stream().map(checkoutProduct -> new Product.Builder().id(checkoutProduct.getProductId())
                .name(checkoutProduct.getProductName())
                .price(checkoutProduct.getPrice())
                .stock(checkoutProduct.getQuantity())
                .offer(checkoutProduct.getOffer())
                .build()).collect(Collectors.toList());

        logger.info("the product informations {}", products);

        double netPrice = products.stream().mapToDouble(product -> product.getPrice()).sum();
        double discountPrice = products.stream().mapToDouble(product -> (product.getPrice() * product.getOffer()) / 100).sum();

        logger.info("the netprice::{}, discount::{}", netPrice, discountPrice);

        checkoutService.removeChechout(checkout);

        return new CheckoutOrder.Builder()
                .userid(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .orderItems(products)
                .netPrice(netPrice)
                .discount(discountPrice)
                .totalPrice(netPrice - discountPrice)
                .build();
    }
}
