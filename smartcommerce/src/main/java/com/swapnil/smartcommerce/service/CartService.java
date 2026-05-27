package com.swapnil.smartcommerce.service;

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

        CartItem cartItem = new CartItem();

        cartItem.setProduct(product);

        cartItem.setQuantity(
                request.getQuantity()
        );

        return cartItem.getProduct().getName()
                + " Qty: "
                + cartItem.getQuantity();
    }
}