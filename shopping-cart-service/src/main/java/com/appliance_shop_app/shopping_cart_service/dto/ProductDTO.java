package com.appliance_shop_app.shopping_cart_service.dto;

import com.appliance_shop_app.shopping_cart_service.model.enums.Status;

public record ProductDTO(
        Long id,
        String name,
        String brand,
        Double price,
        Status status
) {
}
