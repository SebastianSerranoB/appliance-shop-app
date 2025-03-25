package com.appliance_shop_app.sales_service.service;

import com.appliance_shop_app.sales_service.api.ICartAPI;
import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.dto.SaleResponseDTO;
import com.appliance_shop_app.sales_service.mapper.SaleMapper;
import com.appliance_shop_app.sales_service.repository.ISaleRepository;
import com.appliance_shop_app.sales_service.service.validator.SaleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private SaleValidator saleValidator;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private ICartAPI cartAPI;





    @Override
    public List<SaleResponseDTO> getAllSales() {
        return List.of();  //map to sale, return list
    }

    @Override
    public SaleResponseDTO findOneById(Long saleId) {
        return null;  //map and return
    }

    @Override
    public void createSale(CompleteSaleRequestDTO requestDTO) {
        //validate sale and cart, map, save, update cart status on cart-service
    }

    @Override
    public void editSaleStatus(Long cartId, String updatedStatus) {
        //validate for either deleted or canceled status, actualize cart-service
    }




}
