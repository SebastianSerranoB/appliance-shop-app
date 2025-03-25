package com.appliance_shop_app.sales_service.dto;

import jakarta.validation.constraints.*;

public record CompleteSaleRequestDTO(

        @NotNull(message = "Shopping Cart ID is required.")
        @Positive(message = "Shopping Cart ID must be a positive number.")
        Long cartId,

        @NotBlank(message = "Payment method is required.")
        @Pattern(regexp = "(?i)CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER", message = "Invalid category. CASH|CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER")
        String paymentMethod,

        @NotBlank(message = "clientName is required.")
        @Size(max = 30, message = "Name must not exceed 30 characters.")
        String clientName,

        @NotBlank(message = "clientSurname is required")
        @Size(max = 30, message = "surname must not exceed 30 characters.")
        String clientSurname,

        @NotBlank(message = "clientIdentification is required.")
        @Size(max = 20, message = "client identification must not exceed 20 characters.")
        String clientIdentification
) {
}
