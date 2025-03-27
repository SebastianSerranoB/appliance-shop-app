package com.appliance_shop_app.sales_service.service;

import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.dto.SaleResponseDTO;
import com.appliance_shop_app.sales_service.repository.PaymentMethodUsage;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public interface ISaleService {

    public List<SaleResponseDTO> getAllSales();

    public SaleResponseDTO findOneById(Long saleId);

    public void createSale(CompleteSaleRequestDTO requestDTO);

    public void editSaleStatus(Long saleId, String updatedStatus);

    public List<SaleResponseDTO> getTop10SalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    public List<PaymentMethodUsage> getMostUsedPaymentMethods(LocalDateTime startDate, LocalDateTime endDate);

    public Double getTotalAccumulatedSales(LocalDateTime startDate, LocalDateTime endDate);


}
