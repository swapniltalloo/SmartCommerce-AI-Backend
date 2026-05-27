package com.swapnil.smartcommerce.controller;

import com.swapnil.smartcommerce.dto.AddToCartRequest;
import com.swapnil.smartcommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(
            @RequestBody AddToCartRequest request) {

        return cartService.addToCart(request);
    }
}