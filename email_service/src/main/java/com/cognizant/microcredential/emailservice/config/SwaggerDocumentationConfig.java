package com.cognizant.microcredential.emailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@Configuration
public class SwaggerDocumentationConfig {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Email Rest Service API in Spring-Boot 2")
                .description(
                        "A service to sent mails for communicating data like OTP, confirmation code, description etc ...")
                .termsOfServiceUrl("").version("1.0.0").contact(new Contact("Sarath Kumar R", "", "sarath.r93@gmail.com")).build();
    }

    @Bean
    public Docket configureControllerPackageAndConvertors() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                //.paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.cognizant.microcredential.emailservice.controller"))
                .build()
                .apiInfo(apiInfo());
    }
}
