package com.appliance_shop_app.shopping_cart_service.controller;

import com.appliance_shop_app.shopping_cart_service.dto.AddProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCart;
import com.appliance_shop_app.shopping_cart_service.service.IShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/")
public class ShoppingCartController {


    @Autowired
    private IShoppingCartService shoppingCartService;

/*
    //Create a new shopping cart.
    @GetMapping("/create")
    public ResponseEntity<String> createShoppingCart() {

        return ResponseEntity.ok();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShoppingCartResponseDTO>> getAllCarts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    //response a dto, with list of detail
    @GetMapping("/findOne/{id}")
    public ResponseEntity<ShoppingCartResponseDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    //PUT	/carts/{cartId}/products	Add or update a product in the shopping cart.
    @PutMapping("/add-product")
    public ResponseEntity<?> editProduct(@Valid @RequestBody AddProductDTO product, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        productService.updateProduct(product);
        return ResponseEntity.ok("Product added successfully.");
    }

   // PUT	/carts/{cartId}/checkout	Checkout the shopping cart (changes status to CHECKED_OUT).
   @PutMapping("/checkout/{}")
   public ResponseEntity<?> editProduct(@PathVariable Long id)
   {
       productService.updateProduct(product);
       return ResponseEntity.ok("Cart cheked-out successfully, you can proceed to the sale!");
   }


    //Remove a product from the shopping cart. productId
    @DeleteMapping("/remove-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }

    //Delete a shopping cart (e.g., if the user abandons it).
    @DeleteMapping("/remove-cart/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }

    */

}
