package com.appliance_shop_app.sales_service.service.validator;

import com.appliance_shop_app.sales_service.api.ICartAPI;
import com.appliance_shop_app.sales_service.dto.CartResponseDTO;
import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.exception.BusinessException;
import com.appliance_shop_app.sales_service.exception.NotFoundException;
import com.appliance_shop_app.sales_service.model.enums.Status;
import com.appliance_shop_app.sales_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleValidator {

    @Autowired
    private ICartAPI cartAPI;

    @Autowired
    private ISaleRepository saleRepository;


    public void validateCreateSale(CompleteSaleRequestDTO saleRequestDTO) {
        CartResponseDTO cartDTO = fetchCartFromAPI(saleRequestDTO.cartId());
        validateCartStatus(cartDTO, saleRequestDTO.cartId());
    }

    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallback")
    @Retry(name = "cart-service")
    public CartResponseDTO fetchCartFromAPI(Long cartId) {
        return cartAPI.findCartById(cartId);
    }

    public CartResponseDTO fallback(Long cartId, Throwable t){
        throw new BusinessException("Cart-service is unavailable.");
    }


    public void validateCartStatus(CartResponseDTO cartDTO, Long cartId) {
        if (cartDTO == null) {
            throw new NotFoundException("Cart with ID: " + cartId + " not found.");
        }

        if (cartDTO.status().equals(Status.DELETED)) {
            throw new BusinessException("Cart with ID: " + cartId + " has been deleted and cannot be processed.");
        }

        if (!cartDTO.status().equals(Status.CHECKED_OUT)) {
            throw new BusinessException("Cart with ID: " + cartId + " must have a CHECKED_OUT status. Current status: " + cartDTO.status());
        }
    }



    public void validateSale(Long saleId){
        this.saleRepository.findById(saleId).orElseThrow(
                                                            () -> new NotFoundException("Sale with ID: " + saleId + " not found."));
    }


    public void validateEditSaleStatus(Long saleId, String updatedStatus){
        this.validateSale(saleId);
        if(!updatedStatus.toUpperCase().equals(Status.DELETED.toString()) && !updatedStatus.toUpperCase().equals(Status.CANCELED.toString()) ){
            throw new BusinessException("Status must be either DELETED or CANCELED. Status sent: " + updatedStatus);
        }
    }







}
