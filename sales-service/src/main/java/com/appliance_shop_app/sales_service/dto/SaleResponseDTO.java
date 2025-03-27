package com.appliance_shop_app.sales_service.dto;

import com.appliance_shop_app.sales_service.model.enums.PaymentMethod;
import com.appliance_shop_app.sales_service.model.enums.Status;

import java.time.LocalDateTime;

public record SaleResponseDTO(
        Long saleId,
        String clientName,
        String clientIdentification,
        LocalDateTime dateTime,
        PaymentMethod paymentMethod,
        Double fullPrice,
        Status status,
        CartResponseDTO cartDetails
) {
}
