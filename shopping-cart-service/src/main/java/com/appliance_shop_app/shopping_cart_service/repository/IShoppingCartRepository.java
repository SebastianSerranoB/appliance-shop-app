package com.appliance_shop_app.shopping_cart_service.repository;

import com.appliance_shop_app.shopping_cart_service.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
