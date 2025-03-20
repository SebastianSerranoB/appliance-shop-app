package com.appliance_shop_app.shopping_cart_service.mapper;

import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {

    @Autowired
    private ShoppingCartDetailMapper shoppingCartDetailMapper;

    public ShoppingCartResponseDTO toResponseDTO(ShoppingCart shoppingCart){
        return new ShoppingCartResponseDTO(
                shoppingCart.getId(),
                shoppingCart.getFullPrice(),
                shoppingCart.getStatus(),
                shoppingCartDetailMapper.detailListToResponseDTO(shoppingCart.getId()));
    }

}
