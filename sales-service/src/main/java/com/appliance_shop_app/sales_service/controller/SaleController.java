package com.appliance_shop_app.sales_service.controller;


import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.dto.SaleResponseDTO;
import com.appliance_shop_app.sales_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    /*
    public List<SaleResponseDTO> getAllSales();

    public SaleResponseDTO findOneById(Long saleId);

    public void createSale(CompleteSaleRequestDTO requestDTO);

    public void editSaleStatus(Long cartId, String updatedStatus);

    //top sales between a date, statistics.
     */







}
