package com.appliance_shop_app.shopping_cart_service.api;

import com.appliance_shop_app.shopping_cart_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service")
public interface IProductAPI {


    @GetMapping("product/findOne/{id}")
    public ProductDTO getProductById (@PathVariable("id") Long id);

}
