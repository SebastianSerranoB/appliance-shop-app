package com.appliance_shop_app.shopping_cart_service.dto;

import com.appliance_shop_app.shopping_cart_service.model.enums.Status;
import jakarta.validation.constraints.*;

public record ProductDTO(
        @NotNull(message = "Product ID is required.")
        @Positive(message = "Product ID must be a positive number.")
        Long id,

        @NotBlank(message = "Name is required.")
        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String name,

        @NotBlank(message = "Brand is required")
        @Size(max = 30, message = "Brand must not exceed 30 characters.")
        String brand,

        @NotNull(message = "Price is required.")
        @Positive(message = "Price must be a positive number.")
        Double price,

        @NotBlank(message = "Status is required")
        @Pattern(regexp = "(?i)ACTIVE|INACTIVE|DELETED|PENDING", message = "Invalid category. ACTIVE|INACTIVE|DELETED|PENDING")
        Status status
) {
}