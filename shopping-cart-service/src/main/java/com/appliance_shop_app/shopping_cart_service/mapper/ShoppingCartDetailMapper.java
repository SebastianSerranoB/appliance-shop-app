package com.appliance_shop_app.shopping_cart_service.mapper;

import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartProductResponseDTO;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCartDetail;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartDetailRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCartDetailMapper {

    @Autowired
    private IShoppingCartDetailRepository shoppingCartDetailRepository;

    public ShoppingCartProductResponseDTO toResponseDTO(ShoppingCartDetail shoppingCartDetail){
        return new ShoppingCartProductResponseDTO(
                shoppingCartDetail.getProductId(),
                shoppingCartDetail.getProductName(),
                shoppingCartDetail.getProductBrand(),
                shoppingCartDetail.getQuantity(),
                shoppingCartDetail.getUnitPrice()
        );
    }

    public List<ShoppingCartProductResponseDTO> detailListToResponseDTO(Long cartId){
        List<ShoppingCartDetail> auxList =  shoppingCartDetailRepository.findShoppingCartDetailListById(cartId);
        List<ShoppingCartProductResponseDTO> listDTO = new ArrayList<>();

        for(ShoppingCartDetail detail : auxList){
          listDTO.add(this.toResponseDTO(detail));
        }

        return listDTO;
    }

}
