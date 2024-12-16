package org.acme.opentelemetry;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyService {

    private String message;

    @PostConstruct
    public void init() {
        message = "Hello from MyService!";
        System.out.println("MyService initialized.");
    }

    public String getMessage() {
        return message;
    }
}