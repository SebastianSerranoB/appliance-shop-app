package com.appliance_shop_app.sales_service.dto;

public record ProductResponseDTO(
        Long productId,
        String productName,
        String brand,
        int quantity,
        Double unitPrice
) {
}
