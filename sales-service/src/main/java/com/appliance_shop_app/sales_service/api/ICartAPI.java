package com.appliance_shop_app.sales_service.api;

import com.appliance_shop_app.sales_service.dto.CartResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="shopping-cart-service")
public interface ICartAPI {


    @GetMapping("cart/findOne/{id}")
    public CartResponseDTO findCartById(@PathVariable("id") Long id);

    //PutMapping -> cartId and Status -> Only Compeleted, Canceled or Deleted i guess.
    //you have to make the endpoint on cart service
    //change cart status

}
