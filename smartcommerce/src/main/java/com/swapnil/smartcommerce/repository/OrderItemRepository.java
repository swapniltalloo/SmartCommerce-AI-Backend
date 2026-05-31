package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {
}