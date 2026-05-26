package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {
}