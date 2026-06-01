package com.swapnil.smartcommerce.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRevenueDTO {

    private Long totalOrders;

    private Double totalRevenue;

    private Long cancelledOrders;

    private Long placedOrders;

    private Long shippedOrders;
}