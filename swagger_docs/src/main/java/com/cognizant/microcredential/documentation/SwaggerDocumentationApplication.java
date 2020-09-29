package com.cognizant.microcredential.documentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@EnableSwagger2WebMvc
@EnableScheduling
@SpringBootApplication
public class SwaggerDocumentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerDocumentationApplication.class, args);
    }

}
