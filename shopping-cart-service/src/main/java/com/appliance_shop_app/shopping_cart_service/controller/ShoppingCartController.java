package com.appliance_shop_app.shopping_cart_service.controller;

import com.appliance_shop_app.shopping_cart_service.dto.AddProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.appliance_shop_app.shopping_cart_service.service.IShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {


    @Autowired
    private IShoppingCartService shoppingCartService;



    @GetMapping("/createAndGetID")
    public ResponseEntity<String> createShoppingCartAndReturnId() {
        return ResponseEntity.ok("Shopping cart created successfully. Access ID: " +  shoppingCartService.createCartAndGetId());
    }

    @GetMapping("/getAllCartsAndDetails")
    public ResponseEntity<List<ShoppingCartResponseDTO>> getAllCarts() {
        return ResponseEntity.ok(shoppingCartService.getAllCarts());
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<ShoppingCartResponseDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(shoppingCartService.findCartByIdResponse(id));
    }


    @PutMapping("/add-product")
    public ResponseEntity<?> editProduct(@Valid @RequestBody AddProductDTO product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        shoppingCartService.addProductToCart(product);
        return ResponseEntity.ok("Product added successfully.");
    }


   @PutMapping("/checkout/{id}")
   public ResponseEntity<?> editProduct(@PathVariable Long id) {
       shoppingCartService.checkoutCart(id);
       return ResponseEntity.ok("Cart checked-out successfully, you can proceed to the sale!");
   }


    @DeleteMapping("/remove-product")
    public ResponseEntity<String> deleteProduct(@RequestParam Long cartId, @RequestParam Long productId) {
        shoppingCartService.deleteCartProductById(cartId, productId);
        return ResponseEntity.ok("Product deleted successfully.");
    }


    @DeleteMapping("/remove-cart/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        shoppingCartService.deleteCartById(id);
        return ResponseEntity.ok("Cart deleted successfully.");
    }



}
