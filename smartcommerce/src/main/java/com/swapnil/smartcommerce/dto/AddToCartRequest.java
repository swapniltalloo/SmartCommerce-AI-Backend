package com.swapnil.smartcommerce.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddToCartRequest {

    private Long productId;

    private Integer quantity;
}