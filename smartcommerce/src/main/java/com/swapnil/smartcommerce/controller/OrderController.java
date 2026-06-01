package com.swapnil.smartcommerce.controller;
import com.swapnil.smartcommerce.dto.AdminRevenueDTO;
import com.swapnil.smartcommerce.dto.OrderDetailsDTO;
import com.swapnil.smartcommerce.dto.UpdateOrderStatusRequest;
import com.swapnil.smartcommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.swapnil.smartcommerce.dto.OrderResponseDTO;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PutMapping("/{orderId}/cancel")
    public String cancelOrder(
            @PathVariable Long orderId
    ) {

        return orderService.cancelOrder(
                orderId
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{orderId}/status")
    public String updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request
    ) {

        return orderService.updateOrderStatus(
                orderId,
                request
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<OrderResponseDTO> getAllOrders() {

        return orderService.getAllOrders();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/revenue")
    public AdminRevenueDTO getRevenueStats() {

        return orderService.getRevenueStats();
    }
}