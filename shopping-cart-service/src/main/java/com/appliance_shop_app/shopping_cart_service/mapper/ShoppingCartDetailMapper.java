package com.appliance_shop_app.shopping_cart_service.mapper;

import com.appliance_shop_app.shopping_cart_service.api.IProductAPI;
import com.appliance_shop_app.shopping_cart_service.dto.AddProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartProductResponseDTO;
import com.appliance_shop_app.shopping_cart_service.exception.BusinessException;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCart;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCartDetail;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartDetailRepository;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartRepository;
import com.netflix.discovery.converters.Auto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCartDetailMapper {

    @Autowired
    private IShoppingCartDetailRepository shoppingCartDetailRepository;

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private IProductAPI productAPI;

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

    @CircuitBreaker(name = "product-service", fallbackMethod = "fallback")
    @Retry(name = "product-service")
    public ShoppingCartDetail toEntity(AddProductDTO productDTO){

        ProductDTO product = productAPI.getProductById(productDTO.productId());
        ShoppingCart cart = shoppingCartRepository.findById(productDTO.cartId()).orElse(null);

        ShoppingCartDetail detail = new ShoppingCartDetail();
        detail.setCart(cart);
        detail.setProductName(product.name());
        detail.setProductBrand(product.brand());
        detail.setUnitPrice(product.price());
        detail.setQuantity(productDTO.quantity());
        detail.setProductId(productDTO.productId());

        return detail;
    }

    public ShoppingCartDetail fallback(AddProductDTO productDTO, Throwable t) {
        throw new BusinessException("Products-service is unavailable.");
    }



}
