package com.appliance_shop_app.shopping_cart_service.dto;

import com.appliance_shop_app.shopping_cart_service.model.enums.Status;

import java.util.List;

public record ShoppingCartResponseDTO(
        Long cartId,
        Double fullPrice,
        Status status,
        List<ShoppingCartProductResponseDTO> products
) {
}
