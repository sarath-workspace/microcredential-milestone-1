package com.cognizant.microcredential.userservice.controller;

import com.cognizant.microcredential.userservice.model.User;
import com.cognizant.microcredential.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addProduct(@RequestBody @Valid User user) {
        logger.info("The Add user service invoked with the request parameter {}", user.toString());
        return userService.addUser(user);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateProduct(@PathVariable Long id, @RequestBody @Valid User user) {
        logger.info("The Update user service invoked with the id :: {} and values {}", id, user);
        return userService.updateUser(id, user);
    }

    @GetMapping(value = "/view/{userid}")
    public User getUser(
            @PathVariable("userid") long userid
    ) {
        logger.info("The Get user details service was called with the user id {}", userid);
        return userService.viewUser(userid);
    }

}
