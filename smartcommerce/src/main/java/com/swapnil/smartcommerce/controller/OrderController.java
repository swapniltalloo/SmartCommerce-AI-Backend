package com.swapnil.smartcommerce.controller;
import com.swapnil.smartcommerce.dto.OrderDetailsDTO;
import com.swapnil.smartcommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.swapnil.smartcommerce.dto.OrderResponseDTO;
import java.util.List;
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String placeOrder() {

        return orderService.placeOrder();
    }
    @GetMapping
    public List<OrderResponseDTO> getOrders() {

        return orderService.getOrders();
    }
    @GetMapping("/{orderId}")
    public OrderDetailsDTO getOrderDetails(
            @PathVariable Long orderId
    ) {

        return orderService.getOrderDetails(
                orderId
        );
    }
}