package com.appliance_shop_app.products_service.controller;

import com.appliance_shop_app.products_service.dto.CreateProductDTO;
import com.appliance_shop_app.products_service.dto.ModifyProductDTO;
import com.appliance_shop_app.products_service.model.Product;
import com.appliance_shop_app.products_service.service.IProductService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        productService.createProduct(productDTO);
        return ResponseEntity.ok("Product created successfully");
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }



    @GetMapping("/findOne/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }


    @PutMapping("/update")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ModifyProductDTO product, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        productService.updateProduct(product);
        return ResponseEntity.ok("Product modified successfully.");
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully.");
    }







}
