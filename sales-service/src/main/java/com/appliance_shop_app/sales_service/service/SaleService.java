package com.appliance_shop_app.sales_service.service;

import com.appliance_shop_app.sales_service.api.ICartAPI;
import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.dto.SaleResponseDTO;
import com.appliance_shop_app.sales_service.exception.BusinessException;
import com.appliance_shop_app.sales_service.exception.NotFoundException;
import com.appliance_shop_app.sales_service.mapper.SaleMapper;
import com.appliance_shop_app.sales_service.model.Sale;
import com.appliance_shop_app.sales_service.model.enums.Status;
import com.appliance_shop_app.sales_service.repository.ISaleRepository;
import com.appliance_shop_app.sales_service.service.validator.SaleValidator;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
        return this.saleRepository.findAll()
                            .stream()
                            .map(sale -> saleMapper.toDTO(sale)).toList();
    }

    @Override
    public SaleResponseDTO findOneById(Long saleId) {
        return this.saleMapper.toDTO(this.getSale(saleId));
    }

    public Sale getSale(Long saleId){
        return this.saleRepository.findById(saleId).orElseThrow(
                () -> new NotFoundException("Sale with ID: " + saleId + " not found."));
    }

    @Override
    public void createSale(CompleteSaleRequestDTO requestDTO) {
        this.saleValidator.validateCreateSale(requestDTO);
        this.saleRepository.save(this.saleMapper.toEntity(requestDTO));
    }

    @Override
    public void editSaleStatus(Long saleId, String updatedStatus) {
        this.saleValidator.validateEditSaleStatus(saleId, updatedStatus);
        Sale sale = this.getSale(saleId);
        sale.setStatus(Status.valueOf(updatedStatus.toUpperCase()));
        this.saleRepository.save(sale);
        this.updateCartStatus(sale.getCartId(), updatedStatus.toUpperCase());
    }



    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallback")
    @Retry(name = "cart-service")
    public void updateCartStatus(Long cartId, String updatedStatus){
        this.cartAPI.updateStatusAfterSale(cartId, updatedStatus.toUpperCase());
    }

    public void fallback(Long cartId, String updatedStatus, Throwable t){
        throw new BusinessException("Cart-service is unavailable");
    }



}
