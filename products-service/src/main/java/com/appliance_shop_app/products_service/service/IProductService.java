package com.appliance_shop_app.products_service.service;

import com.appliance_shop_app.products_service.dto.CreateProductDTO;
import com.appliance_shop_app.products_service.dto.ModifyProductDTO;
import com.appliance_shop_app.products_service.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();

    public Product findProductById(Long id);

    public void deleteProductById(Long id);

    public void createProduct(CreateProductDTO productDTO);

    public void updateProduct(ModifyProductDTO productDTO);

}
