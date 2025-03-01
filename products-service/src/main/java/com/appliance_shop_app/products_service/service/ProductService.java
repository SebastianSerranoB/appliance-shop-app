package com.appliance_shop_app.products_service.service;

import com.appliance_shop_app.products_service.dto.CreateProductDTO;
import com.appliance_shop_app.products_service.dto.ModifyProductDTO;
import com.appliance_shop_app.products_service.exception.NotFoundException;
import com.appliance_shop_app.products_service.mapper.ProductMapper;
import com.appliance_shop_app.products_service.model.Product;
import com.appliance_shop_app.products_service.model.enums.Status;
import com.appliance_shop_app.products_service.repository.IProductRepository;
import com.appliance_shop_app.products_service.service.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductValidator productValidator;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProductById(Long id) {
        Product auxProduct = productRepository.findById(id).
                                                orElseThrow( () -> new NotFoundException("Product not found. ID: " + id));

        auxProduct.setStatus(Status.DELETED);
        productRepository.save(auxProduct);
    }

    @Override
    public void createProduct(CreateProductDTO productDTO) {
        productValidator.validateCreateProduct(productDTO);
        productRepository.save(productMapper.toEntity(productDTO));
    }

    @Override
    public void updateProduct(ModifyProductDTO productDTO) {
        productValidator.validateUpdateProduct(productDTO);
        productRepository.save(productMapper.updateEntity(
                                                            this.findProductById(productDTO.id()), productDTO));
    }




}
