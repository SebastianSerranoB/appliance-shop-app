package com.appliance_shop_app.shopping_cart_service.service.validator;

import com.appliance_shop_app.shopping_cart_service.api.IProductAPI;
import com.appliance_shop_app.shopping_cart_service.dto.ProductDTO;
import com.appliance_shop_app.shopping_cart_service.exception.BusinessException;
import com.appliance_shop_app.shopping_cart_service.exception.NotFoundException;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCart;
import com.appliance_shop_app.shopping_cart_service.model.enums.Status;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartDetailRepository;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingCartValidator {

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private IShoppingCartDetailRepository shoppingCartDetailRepository;

    @Autowired
    private IProductAPI productAPI;


    public void validateCart(Long cartId, String expectedStatus){
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);

        if(cartOptional.isEmpty()){
            throw new NotFoundException("Cart with ID: " + cartId + " not found.");
        }

        ShoppingCart cart = cartOptional.get();
        if(!cart.getStatus().toString().equals(expectedStatus)){
            throw new BusinessException("Cart is not in the " + expectedStatus + " state.");
        }
    }

    public void validateDeleteCart(Long cartId){
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);

        if(cartOptional.isEmpty()){
            throw new NotFoundException("Cart with ID: " + cartId + " not found.");
        }

        ShoppingCart cart = cartOptional.get();

        if(cart.getStatus().equals(Status.DELETED)){
            throw new BusinessException("Cart with ID: " + cartId + " was already deleted.");
        }

        if(cart.getStatus() != Status.ACTIVE && cart.getStatus() != Status.CHECKED_OUT){
            throw new BusinessException("Cart with ID: " + cartId + " must be in an ACTIVE or CHECKED_OUT status to be deleted. Current status: " + cart.getStatus());
        }
    }

    public void validateUpdateStatusAfterSale(Long cartId, String updatedStatus){
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);

        if(cartOptional.isEmpty()){
            throw new NotFoundException("Cart with ID: " + cartId + " not found.");
        }

        if(!updatedStatus.equals(Status.COMPLETED.toString()) && !updatedStatus.equals(Status.DELETED.toString()) && !updatedStatus.equals(Status.CANCELED.toString())) {
            throw new BusinessException("New status must be either COMPLETED, DELETED or CANCELED after checked_out. Status provided:  " + updatedStatus);
        }

        ShoppingCart cart = cartOptional.get();
        if(cart.getStatus() != Status.CHECKED_OUT  &&  cart.getStatus() != Status.COMPLETED){
            throw new BusinessException("Cart with ID: " + cartId + " must be in a CHECKED_OUT OR COMPLETED status to be updated after sale. Current status: " + cart.getStatus());
        }

    }




    @CircuitBreaker(name = "product-service", fallbackMethod = "fallback")
    @Retry(name = "product-service")
    public void validateProduct(Long productId, String expectedStatus){
        ProductDTO product = productAPI.getProductById(productId);

        if(product == null){
            throw new NotFoundException("Product with ID: " +  productId + " not found.");
        }

        if(!product.status().toString().equals(expectedStatus)){
            throw new BusinessException("Product with ID: " +  productId + " is not in the " + expectedStatus + " state.");
        }
    }

    public void fallback(Long productId, String expectedStatus, Throwable t) {
        throw new BusinessException("Products-service is unavailable.");
    }


    public void validateAddProduct(Long cartId, Long productId){
        this.validateCart(cartId, Status.ACTIVE.toString());
        this.validateProduct(productId,Status.ACTIVE.toString());
    }



    public void validateDeleteProduct(Long cartId, Long productId){
        this.validateCart(cartId, Status.ACTIVE.toString());

        if(!shoppingCartDetailRepository.productExistsInCart(cartId, productId) ){
            throw new NotFoundException("Product with ID: " + productId + " not found on detail of cart with ID: " + cartId);
        }
    }

    public void validateCheckoutCart(Long cartId){
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);

        if(cartOptional.isEmpty()){
            throw  new NotFoundException("Cart with ID: " + cartId + " not found.");
        }

        ShoppingCart cart = cartOptional.get();
        if(!cart.getStatus().equals(Status.ACTIVE)){
            throw new BusinessException("Cart with ID: " + cartId + " is not in the ACTIVE state.");
        }

        this.cartDetailIsEmpty(cartId);
    }

    public void cartDetailIsEmpty(Long cartId){
        if(shoppingCartDetailRepository.findShoppingCartDetailListById(cartId).isEmpty()){
            throw new BusinessException("The cart with ID: " + cartId + " has an empty detail list, it can not be checked out.");
        }
    }

}
