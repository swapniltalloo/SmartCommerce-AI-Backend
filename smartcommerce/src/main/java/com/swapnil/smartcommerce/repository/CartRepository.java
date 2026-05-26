package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository
        extends JpaRepository<Cart, Long> {
}