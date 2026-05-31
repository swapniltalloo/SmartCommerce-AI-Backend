package com.swapnil.smartcommerce.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDTO {

    private Long orderId;

    private String status;

    private Double totalAmount;

    private List<OrderItemDTO> items;
}