package com.appliance_shop_app.sales_service.repository;

import com.appliance_shop_app.sales_service.model.enums.PaymentMethod;

public interface PaymentMethodUsage {
    PaymentMethod getPaymentMethod();
    Long getCount();
}
