package com.appliance_shop_app.sales_service.service;

import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.dto.SaleResponseDTO;

import java.util.List;

public interface ISaleService {

    public List<SaleResponseDTO> getAllSales();

    public SaleResponseDTO findOneById(Long saleId);

    public void createSale(CompleteSaleRequestDTO requestDTO);

    public void editSaleStatus(Long saleId, String updatedStatus);

}
