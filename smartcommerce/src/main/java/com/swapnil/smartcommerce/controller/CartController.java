package com.swapnil.smartcommerce.controller;

import com.swapnil.smartcommerce.dto.AddToCartRequest;
import com.swapnil.smartcommerce.dto.CartSummaryDTO;
import com.swapnil.smartcommerce.dto.UpdateCartRequest;
import com.swapnil.smartcommerce.entity.CartItem;
import com.swapnil.smartcommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.swapnil.smartcommerce.dto.CartResponseDTO;
import com.swapnil.smartcommerce.entity.CartItem;
import java.util.List;
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

    @GetMapping
    public CartSummaryDTO getCart() {

        return cartService.getCart();
    }
    @DeleteMapping("/remove/{productId}")
    public String removeFromCart(
            @PathVariable Long productId) {

        return cartService.removeFromCart(productId);
    }
    @PutMapping("/update")
    public String updateCartItem(
            @RequestBody UpdateCartRequest request) {

        return cartService.updateCartItem(request);
    }
    @DeleteMapping("/clear")
    public String clearCart() {

        return cartService.clearCart();
    }
}