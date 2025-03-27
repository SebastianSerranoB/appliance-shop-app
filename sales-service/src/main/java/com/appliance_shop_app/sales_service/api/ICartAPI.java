package com.appliance_shop_app.sales_service.api;

import com.appliance_shop_app.sales_service.dto.CartResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "shopping-cart-service")
public interface ICartAPI {

    @GetMapping("/cart/findOne/{id}")
    public CartResponseDTO findCartById(@PathVariable("id") Long id);

    @PutMapping("/cart/update-status")
    public void updateStatusAfterSale(@RequestParam Long cartId, @RequestParam String status);

}
