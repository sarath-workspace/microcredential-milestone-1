package com.cognizant.microcredential.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@ComponentScan({"com.cognizant.microcredential"})
public class AuthenticationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }

}
