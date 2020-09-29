package com.cognizant.microcredential.documentation.controller;

import com.cognizant.microcredential.documentation.configuration.ServiceDefinitionContext;
import com.cognizant.microcredential.documentation.configuration.ServiceDescriptionUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerDocumentController {

    private final Logger logger = LoggerFactory.getLogger(SwaggerDocumentController.class);

    @Autowired
    private ServiceDefinitionContext definitionContext;

    @Autowired
    private ServiceDescriptionUpdater serviceDescriptionUpdater;

    @GetMapping("/service/{servicename}")
    public String getServiceDefinition(@PathVariable("servicename") String serviceName) {
        logger.info("Service info retrial service invoked for the service name {}", serviceName);
        return definitionContext.getSwaggerDefinition(serviceName);
    }

    @GetMapping("/refresh")
    public String refresh() {
        logger.info("manual swagger document refresh has been called");
        serviceDescriptionUpdater.refreshSwaggerConfigurations();
        return "Done";
    }
}
