package com.appliance_shop_app.products_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateProductDTO(
        @NotBlank(message = "Name is required.")
        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String name,

        @NotBlank(message = "Brand is required.")
        @Size(max = 30, message = "Brand must not exceed 30 characters.")
        String brand,

        @NotNull(message = "Price is required.")
        @Positive(message = "Price must be greater than 0.")
        Double price
) { }
