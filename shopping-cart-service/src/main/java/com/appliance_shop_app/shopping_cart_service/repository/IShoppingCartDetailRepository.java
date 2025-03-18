package com.appliance_shop_app.shopping_cart_service.repository;

import com.appliance_shop_app.shopping_cart_service.model.ShoppingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShoppingCartDetailRepository extends JpaRepository<ShoppingCartDetail, Long> {

    @Query("SELECT scd FROM ShoppingCartDetail scd WHERE scd.cart.id = :shoppingCartId")
    List<ShoppingCartDetail> findShoppingCartDetailListById(@Param("shoppingCartId") Long shoppingCartId);

    @Query("SELECT COUNT(scd) > 0 FROM ShoppingCartDetail scd WHERE scd.cart.id = :cartId AND scd.productId = :productId")
    boolean productExistsInCart(@Param("cartId") Long cartId, @Param("productId") Long productId);


}
