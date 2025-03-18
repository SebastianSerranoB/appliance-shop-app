package com.appliance_shop_app.shopping_cart_service.dto;

public record ShoppingCartProductResponseDTO(
        Long productId,
        String productName,
        String brand,
        int quantity,
        Double unitPrice
) {
}
