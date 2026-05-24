package com.swapnil.smartcommerce.repository;

import com.swapnil.smartcommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}