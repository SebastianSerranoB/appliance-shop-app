package com.appliance_shop_app.products_service.service.validator;

import com.appliance_shop_app.products_service.dto.CreateProductDTO;
import com.appliance_shop_app.products_service.dto.ModifyProductDTO;
import com.appliance_shop_app.products_service.exception.BusinessException;
import com.appliance_shop_app.products_service.exception.NotFoundException;
import com.appliance_shop_app.products_service.model.Product;
import com.appliance_shop_app.products_service.model.enums.Status;
import com.appliance_shop_app.products_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    @Autowired
    private IProductRepository productRepository;

    public void validateCreateProduct(CreateProductDTO dto){
        if (productRepository.existsByNameAndBrand(dto.name(), dto.brand() ) ) {
            throw new BusinessException("Product with name " + dto.name() + " and brand " + dto.brand() + " already exists.");
        }
    }

    public void validateUpdateProduct(ModifyProductDTO dto){
        Product auxProduct = productRepository.findById(dto.id()).
                orElseThrow( () -> new NotFoundException("Product not found. ID: " + dto.id()) );

        if(dto.brand() != null && dto.name() != null) {
            if(!auxProduct.getName().equalsIgnoreCase(dto.name()) || !auxProduct.getBrand().equalsIgnoreCase(dto.brand()) ) {
                if(productRepository.existsByNameAndBrand(dto.name(), dto.brand()) ) {
                    throw new BusinessException("Product with name " + dto.name() + " and brand " + dto.brand() + " already exists.");
                }
            }
        }

        if(dto.status() != null) {
            try {
                Status status = Status.valueOf(dto.status().toUpperCase());

                if (status == Status.DELETED) {
                    throw new BusinessException("Status 'DELETED' is not allowed on updates. Use delete endpoint instead.");
                }

            } catch(IllegalArgumentException e){
                throw  new IllegalArgumentException("Invalid status: " + dto.status() );
            }
        }
    }






}
