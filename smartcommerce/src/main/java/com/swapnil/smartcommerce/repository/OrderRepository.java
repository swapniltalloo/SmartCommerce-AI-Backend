package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.Order;
import com.swapnil.smartcommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
    Optional<Order> findByIdAndUser(
            Long id,
            User user
    );
}