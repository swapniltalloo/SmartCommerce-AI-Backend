package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}