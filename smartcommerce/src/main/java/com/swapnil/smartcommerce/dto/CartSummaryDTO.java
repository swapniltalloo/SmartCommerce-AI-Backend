package com.swapnil.smartcommerce.dto;

import com.swapnil.smartcommerce.dto.CartResponseDTO;
import lombok.Getter;
import lombok.*;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartSummaryDTO {

    private List<CartResponseDTO> items;

    private Double totalAmount;
}