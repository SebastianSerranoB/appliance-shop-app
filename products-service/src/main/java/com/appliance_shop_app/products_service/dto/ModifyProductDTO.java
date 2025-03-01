package com.appliance_shop_app.products_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModifyProductDTO(
        @NotNull
        @Positive
        Long id,

        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String name,

        @Size(max = 30, message = "Brand must not exceed 30 characters.")
        String brand,

        @Positive(message = "Price must be greater than 0.")
        Double price,

        @NotNull(message = "status must not be null.")
        String status
) { }
