package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import com.swapnil.smartcommerce.entity.Cart;
import com.swapnil.smartcommerce.entity.Product;

import java.util.Optional;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(
            Cart cart,
            Product product
    );}