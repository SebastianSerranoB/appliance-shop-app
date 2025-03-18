package com.appliance_shop_app.shopping_cart_service.service;

import com.appliance_shop_app.shopping_cart_service.api.IProductAPI;
import com.appliance_shop_app.shopping_cart_service.dto.AddProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartProductResponseDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartDetailRepository;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartRepository;
import com.appliance_shop_app.shopping_cart_service.service.validator.ShoppingCartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private IShoppingCartDetailRepository shoppingCartDetailRepository;

    @Autowired
    private ShoppingCartValidator shoppingCartValidator;

    @Autowired
    private IProductAPI productAPI;


    @Override
    public List<ShoppingCartResponseDTO> getAllCarts() {
        return List.of();
    }

    @Override
    public ShoppingCartProductResponseDTO findCartById(Long id) {
        return null;
    }

    @Override
    public Long createCartAndGetId() {
        return 0;
    }

    @Override
    public void addProductToCart(AddProductDTO productDTO) {

    }

    @Override
    public void checkoutCart(Long id) {

    }

    @Override
    public void deleteCartProductById(Long id) {

    }

    @Override
    public void deleteCartById(Long id) {

    }
}
