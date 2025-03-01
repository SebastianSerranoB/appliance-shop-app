package com.appliance_shop_app.products_service.mapper;

import com.appliance_shop_app.products_service.dto.CreateProductDTO;
import com.appliance_shop_app.products_service.dto.ModifyProductDTO;
import com.appliance_shop_app.products_service.model.Product;
import com.appliance_shop_app.products_service.model.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductDTO dto){
        Product product = new Product();
        product.setName(dto.name());
        product.setBrand(dto.brand());
        product.setPrice(dto.price());
        product.setStatus(Status.ACTIVE);

        return product;
    }

    public Product updateEntity(Product product, ModifyProductDTO dto){
        if (dto.name() != null) product.setName(dto.name());
        if (dto.brand() != null) product.setBrand(dto.brand());
        if (dto.price() != null) product.setPrice(dto.price());
        if (dto.status() != null) product.setStatus(Status.valueOf(dto.status().toUpperCase()));

        return product;
    }










}
