package com.appliance_shop_app.shopping_cart_service.service;

import com.appliance_shop_app.shopping_cart_service.api.IProductAPI;
import com.appliance_shop_app.shopping_cart_service.dto.AddProductDTO;
import com.appliance_shop_app.shopping_cart_service.dto.ShoppingCartResponseDTO;
import com.appliance_shop_app.shopping_cart_service.exception.BusinessException;
import com.appliance_shop_app.shopping_cart_service.exception.NotFoundException;
import com.appliance_shop_app.shopping_cart_service.mapper.ShoppingCartDetailMapper;
import com.appliance_shop_app.shopping_cart_service.mapper.ShoppingCartMapper;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCart;
import com.appliance_shop_app.shopping_cart_service.model.ShoppingCartDetail;
import com.appliance_shop_app.shopping_cart_service.model.enums.Status;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartDetailRepository;
import com.appliance_shop_app.shopping_cart_service.repository.IShoppingCartRepository;
import com.appliance_shop_app.shopping_cart_service.service.validator.ShoppingCartValidator;
import com.netflix.discovery.converters.Auto;
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
    private ShoppingCartDetailMapper shoppingCartDetailMapper,

    @Autowired
    private IProductAPI productAPI;


    @Override
    public List<ShoppingCartResponseDTO> getAllCarts() {
        return shoppingCartRepository.findAll().stream()
                .map(cart -> shoppingCartMapper.toResponseDTO(cart))
                .toList();
    }

    @Override
    public ShoppingCartResponseDTO findCartById(Long id) {
        return this.shoppingCartMapper.toResponseDTO(this.shoppingCartRepository.findById(id)
                                                                                .orElseThrow(() -> new NotFoundException("Cart with ID: " + id + " not found.")));
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
       this.addQuantityToExistingProduct(productDTO);
       this.shoppingCartDetailRepository.save(this.shoppingCartDetailMapper.toEntity(productDTO));
       this.calculateCartFullPrice(productDTO.cartId());
    }

    private void addQuantityToExistingProduct(AddProductDTO productDTO) {
        List<ShoppingCartDetail> detailList = this.shoppingCartDetailRepository.findShoppingCartDetailListById(productDTO.cartId());
        if(!detailList.isEmpty()){
            for(ShoppingCartDetail detail :detailList){
                if(detail.getProductId().equals(productDTO.productId()) ){
                    detail.setQuantity(detail.getQuantity() + productDTO.quantity());
                    this.shoppingCartDetailRepository.save(detail);
                    break;
                }
            }
        }
    }

    @Override
    public void checkoutCart(Long id) {
        //validate checkout, must habe products, cart exists, correct sate
        //change to checkedout state
    }


    @Override
    public void deleteCartProductById(Long cartId, Long productId) {
        //validate cart exists and it is active, check for product in cart, delete, recalculate full price.
    }

    @Override
    public void deleteCartById(Long id) {
        //validate it exists and it is not completed, change state to deleted or something like that, logic delete
    }

    public void calculateCartFullPrice(Long cartId){
        List<ShoppingCartDetail> detailList = this.shoppingCartDetailRepository.findShoppingCartDetailListById(cartId);
        Double acumulatePrice = 0D;

        if(!detailList.isEmpty()){
            for(ShoppingCartDetail detail : detailList){
               acumulatePrice +=  detail.getQuantity() * detail.getUnitPrice();
            }
        }

        ShoppingCart cart =  this.shoppingCartRepository.findById(id);
        cart.setFullPrice(acumulatePrice);
        this.shoppingCartRepository.save(cart);
    }


}
