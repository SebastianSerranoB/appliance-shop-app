package com.appliance_shop_app.shopping_cart_service.service;

import com.appliance_shop_app.shopping_cart_service.dto.AddProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartProductResponseDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartResponseDTO;

import java.util.List;

public interface IShoppingCartService {

    public List<ShoppingCartResponseDTO> getAllCarts();

    public ShoppingCartResponseDTO findCartByIdResponse(Long id);

    public Long createCartAndGetId();

    public void addProductToCart(AddProductDTO productDTO);

    public void checkoutCart(Long id);

    public void deleteCartProductById(Long cartId, Long productId);

    public void deleteCartById(Long id);
}
