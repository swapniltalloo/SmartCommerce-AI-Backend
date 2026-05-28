package com.swapnil.smartcommerce.service;

import com.swapnil.smartcommerce.entity.Cart;
import com.swapnil.smartcommerce.repository.CartItemRepository;
import com.swapnil.smartcommerce.repository.CartRepository;
import com.swapnil.smartcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swapnil.smartcommerce.dto.AddToCartRequest;
import com.swapnil.smartcommerce.dto.AddToCartRequest;
import com.swapnil.smartcommerce.entity.Product;
import com.swapnil.smartcommerce.entity.CartItem;
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;
    public String addToCart(AddToCartRequest request) {

        Product product = productRepository.findById(
                request.getProductId()
        ).orElseThrow(() ->
                new RuntimeException("Product not found")
        );

        Cart cart = cartRepository.findById(1L)
                .orElseThrow(() ->
                        new RuntimeException("Cart not found")
                );

        CartItem cartItem = new CartItem();

        cartItem.setProduct(product);

        cartItem.setCart(cart);

        cartItem.setQuantity(
                request.getQuantity()
        );

        cartItemRepository.save(cartItem);

        return "Product added to cart successfully";
    }}