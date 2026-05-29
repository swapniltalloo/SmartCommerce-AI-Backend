package com.swapnil.smartcommerce.service;

import com.swapnil.smartcommerce.entity.Cart;
import com.swapnil.smartcommerce.repository.CartItemRepository;
import com.swapnil.smartcommerce.repository.CartRepository;
import com.swapnil.smartcommerce.repository.ProductRepository;
import com.swapnil.smartcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swapnil.smartcommerce.dto.AddToCartRequest;
import com.swapnil.smartcommerce.dto.AddToCartRequest;
import com.swapnil.smartcommerce.dto.CartResponseDTO;
import java.util.stream.Collectors;
import com.swapnil.smartcommerce.entity.Product;
import com.swapnil.smartcommerce.entity.CartItem;
import com.swapnil.smartcommerce.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import com.swapnil.smartcommerce.entity.CartItem;
@Service

public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    public String addToCart(AddToCartRequest request) {

        Product product = productRepository.findById(
                request.getProductId()
        ).orElseThrow(() ->
                new RuntimeException("Product not found")
        );
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
        CartItem cartItem =
                cartItemRepository
                        .findByCartAndProduct(cart, product)
                        .orElse(null);

        if (cartItem != null) {

            cartItem.setQuantity(
                    cartItem.getQuantity()
                            + request.getQuantity()
            );

        } else {

            cartItem = new CartItem();

            cartItem.setCart(cart);

            cartItem.setProduct(product);

            cartItem.setQuantity(
                    request.getQuantity()
            );
        }

        cartItemRepository.save(cartItem);
        return "Product added to cart successfully";

    }public List<CartResponseDTO> getCart() {

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

        return cart.getCartItems()
                .stream()
                .map(cartItem -> CartResponseDTO.builder()
                        .productName(
                                cartItem.getProduct().getName()
                        )
                        .price(
                                cartItem.getProduct().getPrice()
                        )
                        .quantity(
                                cartItem.getQuantity()
                        )
                        .build()
                )
                .collect(Collectors.toList());
        }
    }
