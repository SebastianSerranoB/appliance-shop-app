package com.appliance_shop_app.shopping_cart_service.service;

import com.appliance_shop_app.shopping_cart_service.api.IProductAPI;
import com.appliance_shop_app.shopping_cart_service.dto.AddProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.appliance_shop_app.shopping_cart_service.exception.NotFoundException;
import com.appliance_shop_app.shopping_cart_service.mapper.ShoppingCartDetailMapper;
import com.appliance_shop_app.shopping_cart_service.mapper.ShoppingCartMapper;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCart;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCartDetail;
import com.appliance_shop_app.shopping_cart_service.model.enums.Status;
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
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private ShoppingCartDetailMapper shoppingCartDetailMapper;

    @Autowired
    private IProductAPI productAPI;


    @Override
    public List<ShoppingCartResponseDTO> getAllCarts() {
        return shoppingCartRepository.findAll().stream()
                .map(cart -> shoppingCartMapper.toResponseDTO(cart))
                .toList();
    }

    @Override
    public ShoppingCartResponseDTO findCartByIdResponse(Long id) {
        return this.shoppingCartMapper.toResponseDTO(this.findCartById(id));
    }

    @Override
    public Long createCartAndGetId() {
        ShoppingCart cart = new ShoppingCart();
        cart.setStatus(Status.ACTIVE);
        return shoppingCartRepository.save(cart).getId();
    }


    @Override
    public void addProductToCart(AddProductDTO productDTO) {
       this.shoppingCartValidator.validateAddProduct(productDTO.cartId(), productDTO.productId());
       if(!this.addQuantityToExistingProduct(productDTO) ){
           this.shoppingCartDetailRepository.save(this.shoppingCartDetailMapper.toEntity(productDTO));
       }

       this.calculateCartFullPrice(productDTO.cartId());
    }


    private Boolean addQuantityToExistingProduct(AddProductDTO productDTO) {
        List<ShoppingCartDetail> detailList = this.shoppingCartDetailRepository.findShoppingCartDetailListById(productDTO.cartId());
        if(!detailList.isEmpty()){
            for(ShoppingCartDetail detail :detailList){
                if(detail.getProductId().equals(productDTO.productId()) ){
                    detail.setQuantity(detail.getQuantity() + productDTO.quantity());
                    this.shoppingCartDetailRepository.save(detail);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void checkoutCart(Long id) {
        this.shoppingCartValidator.validateCheckoutCart(id);
        ShoppingCart cart = this.findCartById(id);
        cart.setStatus(Status.CHECKED_OUT);
        this.shoppingCartRepository.save(cart);
    }

    public void updateStatusAfterSale(Long cartId, String updatedStatus){
        this.shoppingCartValidator.validateUpdateStatusAfterSale(cartId, updatedStatus);
        ShoppingCart cart = this.findCartById(cartId);
        cart.setStatus(this.shoppingCartMapper.stringToStatus(updatedStatus));
        this.shoppingCartRepository.save(cart);
    }


    @Override
    public void deleteCartProductById(Long cartId, Long productId) {
        this.shoppingCartValidator.validateDeleteProduct(cartId, productId);
        this.deleteCartProduct(cartId, productId);
        this.calculateCartFullPrice(cartId);
    }


    public void deleteCartProduct(Long cartId, Long productId){
        List<ShoppingCartDetail> detailList = this.shoppingCartDetailRepository.findShoppingCartDetailListById(cartId);

        for(ShoppingCartDetail detail : detailList){
            if(detail.getProductId().equals(productId)){
                this.shoppingCartDetailRepository.delete(detail);
            }
        }
    }




    @Override
    public void deleteCartById(Long id) {
        this.shoppingCartValidator.validateDeleteCart(id);
        ShoppingCart cart = this.findCartById(id);
        cart.setStatus(Status.DELETED);
        this.shoppingCartRepository.save(cart);
    }


    public ShoppingCart findCartById(Long id){
        return this.shoppingCartRepository.findById(id).orElseThrow( () -> new NotFoundException("Cart with ID: " + id + " not found,"));
    }


    public void calculateCartFullPrice(Long cartId){
        List<ShoppingCartDetail> detailList = this.shoppingCartDetailRepository.findShoppingCartDetailListById(cartId);
        Double acumulatePrice = 0D;

        if(!detailList.isEmpty()){
            for(ShoppingCartDetail detail : detailList){
               acumulatePrice +=  detail.getQuantity() * detail.getUnitPrice();
            }
        }

        ShoppingCart cart =  this.findCartById(cartId);
        cart.setFullPrice(acumulatePrice);
        this.shoppingCartRepository.save(cart);
    }


}
