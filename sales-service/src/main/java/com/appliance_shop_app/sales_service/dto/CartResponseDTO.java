package com.appliance_shop_app.sales_service.dto;

import com.appliance_shop_app.sales_service.model.enums.Status;

import java.util.List;

public record CartResponseDTO(
        Long cartId,
        Double fullPrice,
        Status status,
        List<ProductResponseDTO> products
) {
}
