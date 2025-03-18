package com.appliance_shop_app.shopping_cart_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record AddProductDTO(

        @NotNull(message = "Product id is required.")
        @Positive(message = "Product id must be a positive number.")
        Long productId,

        @NotNull(message = "Quantity  is required.")
        @Positive(message = "Quantity must be a positive number.")
        int quantity,

        @NotNull(message = "Cart  id is required.")
        @Positive(message = "Cart id must be a positive number.")
        Long cartId
){ }
