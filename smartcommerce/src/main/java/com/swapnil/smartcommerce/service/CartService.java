package com.swapnil.smartcommerce.service;

import com.swapnil.smartcommerce.dto.*;
import com.swapnil.smartcommerce.entity.Cart;
import com.swapnil.smartcommerce.repository.CartItemRepository;
import com.swapnil.smartcommerce.repository.CartRepository;
import com.swapnil.smartcommerce.repository.ProductRepository;
import com.swapnil.smartcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swapnil.smartcommerce.dto.AddToCartRequest;
import java.util.List;
import java.util.stream.Collectors;
import com.swapnil.smartcommerce.dto.CartSummaryDTO;
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

    }public CartSummaryDTO getCart() {

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

        List<CartResponseDTO> items =
                cart.getCartItems()
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

        Double totalAmount =
                cart.getCartItems()
                        .stream()
                        .mapToDouble(cartItem ->
                                cartItem.getProduct().getPrice()
                                        * cartItem.getQuantity()
                        )
                        .sum();

        return CartSummaryDTO.builder()
                .items(items)
                .totalAmount(totalAmount)
                .build();
    }public String removeFromCart(Long productId) {

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

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"
                                )
                        );

        CartItem cartItem =
                cartItemRepository
                        .findByCartAndProduct(cart, product)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found in cart"
                                )
                        );

        cartItemRepository.delete(cartItem);

        return "Product removed from cart";
    }
    public String updateCartItem(
            UpdateCartRequest request) {

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

        Product product =
                productRepository.findById(
                                request.getProductId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found"
                                )
                        );

        CartItem cartItem =
                cartItemRepository
                        .findByCartAndProduct(
                                cart,
                                product
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found in cart"
                                )
                        );

        cartItem.setQuantity(
                request.getQuantity()
        );

        cartItemRepository.save(cartItem);

        return "Cart item updated successfully";
    }
    public String clearCart() {

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

        cartItemRepository.deleteAll(cartItems);

        return "Cart cleared successfully";
    }

    }
