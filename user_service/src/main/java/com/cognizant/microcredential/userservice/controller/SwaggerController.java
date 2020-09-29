package com.cognizant.microcredential.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SwaggerController {

    private Logger logger = LoggerFactory.getLogger(SwaggerController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.info("entering into swagger UI controller {}", "test");
        return "redirect:swagger-ui.html";
    }
}
