package com.swapnil.smartcommerce.service;
import com.swapnil.smartcommerce.dto.*;
import com.swapnil.smartcommerce.entity.Order;
import com.swapnil.smartcommerce.entity.Cart;
import com.swapnil.smartcommerce.entity.CartItem;
import com.swapnil.smartcommerce.entity.User;
import com.swapnil.smartcommerce.entity.OrderItem;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.swapnil.smartcommerce.entity.Order;
import java.util.List;
import java.util.List;
import com.swapnil.smartcommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swapnil.smartcommerce.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;
    public String placeOrder() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        Cart cart =
                cartRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart not found"
                                )
                        );

        List<CartItem> cartItems =
                cartItemRepository.findByCart(cart);

        Double totalAmount =
                cartItems.stream()
                        .mapToDouble(cartItem ->
                                cartItem.getProduct().getPrice()
                                        * cartItem.getQuantity()
                        )
                        .sum();

        Order order = new Order();

        order.setUser(user);

        order.setTotalAmount(totalAmount);

        order.setStatus("PLACED");

        orderRepository.save(order);
        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);

            orderItem.setProduct(
                    cartItem.getProduct()
            );

            orderItem.setQuantity(
                    cartItem.getQuantity()
            );

            orderItem.setPrice(
                    cartItem.getProduct().getPrice()
            );

            orderItemRepository.save(orderItem);
        }
        cartItemRepository.deleteAll(cartItems);
        return "Order created successfully";
    }
    public List<OrderResponseDTO> getOrders() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        List<Order> orders =
                orderRepository.findByUser(user);

        return orders.stream()
                .map(order ->
                        OrderResponseDTO.builder()
                                .orderId(order.getId())
                                .totalAmount(
                                        order.getTotalAmount()
                                )
                                .status(
                                        order.getStatus()
                                )
                                .build()
                )
                .toList();
    }
    public OrderDetailsDTO getOrderDetails(
            Long orderId
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        Order order =
                orderRepository
                        .findByIdAndUser(
                                orderId,
                                user
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found"
                                )
                        );

        List<OrderItemDTO> items =
                order.getOrderItems()
                        .stream()
                        .map(orderItem ->
                                OrderItemDTO.builder()
                                        .productName(
                                                orderItem.getProduct().getName()
                                        )
                                        .quantity(
                                                orderItem.getQuantity()
                                        )
                                        .price(
                                                orderItem.getPrice()
                                        )
                                        .build()
                        )
                        .toList();

        return OrderDetailsDTO.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .totalAmount(
                        order.getTotalAmount()
                )
                .items(items)
                .build();
    }
    public String cancelOrder(
            Long orderId
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String username =
                authentication.getName();

        User user =
                userRepository.findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        Order order =
                orderRepository
                        .findByIdAndUser(
                                orderId,
                                user
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found"
                                )
                        );

        order.setStatus("CANCELLED");

        orderRepository.save(order);

        return "Order cancelled successfully";
    }
    public String updateOrderStatus(
            Long orderId,
            UpdateOrderStatusRequest request
    ) {

        Order order =
                orderRepository.findById(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found"
                                )
                        );

        order.setStatus(
                request.getStatus()
        );

        orderRepository.save(order);

        return "Order status updated";
    }
    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orders =
                orderRepository.findAll();

        return orders.stream()
                .map(order ->
                        OrderResponseDTO.builder()
                                .orderId(order.getId())
                                .totalAmount(
                                        order.getTotalAmount()
                                )
                                .status(
                                        order.getStatus()
                                )
                                .build()
                )
                .toList();
    }
    public AdminRevenueDTO getRevenueStats() {

        List<Order> orders =
                orderRepository.findAll();

        long totalOrders =
                orders.size();

        double totalRevenue =
                orders.stream()
                        .filter(order ->
                                !"CANCELLED".equals(
                                        order.getStatus()
                                )
                        )
                        .mapToDouble(
                                Order::getTotalAmount
                        )
                        .sum();

        long cancelledOrders =
                orders.stream()
                        .filter(order ->
                                "CANCELLED".equals(
                                        order.getStatus()
                                )
                        )
                        .count();

        long placedOrders =
                orders.stream()
                        .filter(order ->
                                "PLACED".equals(
                                        order.getStatus()
                                )
                        )
                        .count();

        long shippedOrders =
                orders.stream()
                        .filter(order ->
                                "SHIPPED".equals(
                                        order.getStatus()
                                )
                        )
                        .count();

        return AdminRevenueDTO.builder()
                .totalOrders(totalOrders)
                .totalRevenue(totalRevenue)
                .cancelledOrders(
                        cancelledOrders
                )
                .placedOrders(
                        placedOrders
                )
                .shippedOrders(
                        shippedOrders
                )
                .build();
    }

}