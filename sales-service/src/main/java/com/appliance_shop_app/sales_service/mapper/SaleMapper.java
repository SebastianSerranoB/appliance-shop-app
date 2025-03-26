package com.appliance_shop_app.sales_service.mapper;

import com.appliance_shop_app.sales_service.api.ICartAPI;
import com.appliance_shop_app.sales_service.dto.CartResponseDTO;
import com.appliance_shop_app.sales_service.dto.CompleteSaleRequestDTO;
import com.appliance_shop_app.sales_service.dto.SaleResponseDTO;
import com.appliance_shop_app.sales_service.exception.BusinessException;
import com.appliance_shop_app.sales_service.model.Sale;
import com.appliance_shop_app.sales_service.model.enums.PaymentMethod;
import com.appliance_shop_app.sales_service.model.enums.Status;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class SaleMapper {

    @Autowired
    private ICartAPI cartAPI;


    public Sale toEntity(CompleteSaleRequestDTO requestDTO){
        Sale newSale = new Sale();
        newSale.setCartId(requestDTO.cartId());
        newSale.setDate(LocalDateTime.now());
        newSale.setStatus(Status.COMPLETED);
        newSale.setClientName(requestDTO.clientName());
        newSale.setClientSurname(requestDTO.clientSurname());
        newSale.setClientIdentification(requestDTO.clientIdentification());
        newSale.setPaymentMethod(PaymentMethod.valueOf(requestDTO.paymentMethod().toUpperCase()));

        newSale.setFullPrice(this.getCarFullPrice(requestDTO.cartId()));
        this.updateCartStatus(requestDTO.cartId());

        return newSale;
    }

    public SaleResponseDTO toDTO(Sale sale){

        SaleResponseDTO saleDTO = new SaleResponseDTO(
                sale.getClientName() + " " + sale.getClientSurname(),
                sale.getClientIdentification(),
                sale.getDate(),
                sale.getPaymentMethod(),
                sale.getFullPrice(),
                sale.getStatus(),
                this.getCart(sale.getCartId())
        );

        return saleDTO;
    }



    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackGetCart")
    @Retry(name = "cart-service")
    public CartResponseDTO getCart(Long cartId){
       return this.cartAPI.findCartById(cartId);
    }

    public CartResponseDTO fallbackGetCart(Long cartId, Throwable t){
        throw new BusinessException("Cart-service is unavailable.");
    }








    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackCartStatus")
    @Retry(name = "cart-service")
    public void updateCartStatus(Long cartId){
        this.cartAPI.updateStatusAfterSale(cartId, Status.COMPLETED.toString());
    }

    public void fallbackCartStatus(Long cartId, Throwable t){
        throw new BusinessException("Cart-service is unavailable.");
    }


    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackCartPrice")
    @Retry(name = "cart-service")
    public Double getCarFullPrice(Long cartId){
       return this.cartAPI.findCartById(cartId).fullPrice();
    }

    public Double fallbackCartPrice(Long cartId, Throwable t){
        return 0.0;
    }






}
