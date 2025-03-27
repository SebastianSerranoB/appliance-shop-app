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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        List<Sale> results = this.saleRepository.findAll();
        if(results.isEmpty()){
            throw new BusinessException("No sales exist between the time period.");
        }else{
            return this.saleRepository.findAll()
                    .stream()
                    .map(sale -> saleMapper.toDTO(sale)).toList();
        }

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
        this.updateCartStatus(sale.getCartId(), updatedStatus);
    }



    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackUpdateStatus")
    @Retry(name = "cart-service")
    public void updateCartStatus(Long cartId, String updatedStatus){
        this.cartAPI.updateStatusAfterSale(cartId, updatedStatus);
    }

    public void fallbackUpdateStatus(Long cartId, String updatedStatus, Throwable t){
        throw new BusinessException("Cart-service is unavailable");
    }



    public List<SaleResponseDTO> getTop10SalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> results = saleRepository.findTop10SalesBetweenDates(Status.COMPLETED, startDate, endDate, PageRequest.of(0,10));

        if(!results.isEmpty()) {
            return results.stream().map(sale -> saleMapper.toDTO(sale)).toList();
        }else{
            throw new BusinessException("No sales exist between the time period.");
        }

    }


    public Double getTotalAccumulatedSales(LocalDateTime startDate, LocalDateTime endDate) {
        Double value =  saleRepository.findTotalAccumulatedSales(Status.COMPLETED, startDate, endDate);
        if(value != null){
            return value;
        }else{
            throw new BusinessException("No sales exist between the time period.");
        }
    }



    public String getMostUsedPaymentMethod(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = saleRepository.findMostUsedPaymentMethod(Status.COMPLETED, startDate, endDate);

        if (results != null && !results.isEmpty()) {
            Object[] result = results.getFirst();
            String paymentMethod = result[0].toString();
            Long count = (Long) result[1];

            return "The most used payment method was " + paymentMethod + " and it was used " + count + " times.";
        } else {
            throw new BusinessException("No sales found between the given dates.");
        }
    }

}
