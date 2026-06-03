package com.swapnil.smartcommerce.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public String health() {
        return "SmartCommerce API is running";
    }
}