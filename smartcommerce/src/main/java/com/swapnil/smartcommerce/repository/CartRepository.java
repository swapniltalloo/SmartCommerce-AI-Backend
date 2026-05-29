package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.Cart;
import com.swapnil.smartcommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository
        extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);
}