package com.tlsrk.apps.ecom.services.impl;

import com.tlsrk.apps.ecom.dto.CartItem;
import com.tlsrk.apps.ecom.dto.Product;
import com.tlsrk.apps.ecom.entities.CartEntity;
import com.tlsrk.apps.ecom.entities.ProductEntity;
import com.tlsrk.apps.ecom.entities.UserEntity;
import com.tlsrk.apps.ecom.repo.CartRepository;
import com.tlsrk.apps.ecom.repo.ProductRepository;
import com.tlsrk.apps.ecom.repo.UserRepository;
import com.tlsrk.apps.ecom.services.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartItem addToCart(String userId, CartItem cartItem) {
        UserEntity user = validateUser(userId);
        ProductEntity product = validateProduct(cartItem.getProductId());
        if (user != null && product != null) {
            return updateCart(userId, cartItem, user, product);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean removeFromCart(String userId, Long productId) {
        UserEntity user = validateUser(userId);
        ProductEntity product = validateProduct(productId);
        if (user != null && product != null) {
            cartRepository.deleteByUserAndProduct(user, product);
            return true;
        }else  {
            return false;
        }
    }

    @Override
    public List<CartItem> getCartItems(String userId) {
        List<CartItem> cartItemList = new ArrayList<>();
        UserEntity user = validateUser(userId);
        if(user!=null){
            List<CartEntity> entityList = cartRepository.findByUser(user);
            entityList.forEach(entity -> {
                CartItem cartItem = new CartItem();
                cartItem.setQuantity(entity.getQuantity());
                cartItem.setProductId(entity.getProduct().getId());
                cartItem.setUserId(entity.getUser().getId());
                cartItem.setTotalPrice(entity.getTotalPrice());
                cartItem.setProductDetails(mapProductEntityToProduct(entity.getProduct()));
                cartItemList.add(cartItem);
            });
        }
        return cartItemList;
    }

    private ProductEntity validateProduct(Long productId) {
        Optional<ProductEntity> optProd = productRepository.findById(Long.valueOf(productId));
        if (!optProd.isPresent()) {
            return null;
        } else {
            return optProd.get();
        }
    }

    private UserEntity validateUser(String userId) {
        Optional<UserEntity> optUser = userRepository.findById(Long.valueOf(userId));
        if (!optUser.isPresent()) {
            return null;
        } else {
            return optUser.get();
        }
    }

    private CartItem updateCart(String userId, CartItem cartItem, UserEntity user, ProductEntity product) {
        /** Check if already cart has entry with same user & product. If exists just increase the quantity, else add new entry **/
        Optional<CartEntity> optCart = cartRepository.findByUserAndProduct(user, product);
        CartEntity cartEntity;
        if (optCart.isPresent()) {
            cartEntity = optCart.get();
            cartEntity.setQuantity(cartEntity.getQuantity() + cartItem.getQuantity());
            cartEntity.setTotalPrice(product.getPrice().multiply(new BigDecimal(cartEntity.getQuantity())));
        } else {
            cartEntity = new CartEntity();
            cartEntity.setProduct(product);
            cartEntity.setUser(user);
            cartEntity.setQuantity(cartItem.getQuantity());
            cartEntity.setTotalPrice(product.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        }
        cartRepository.save(cartEntity);
        cartItem.setQuantity(cartEntity.getQuantity());
        cartItem.setTotalPrice(cartEntity.getTotalPrice());
        return cartItem;
    }

    private Product mapProductEntityToProduct(ProductEntity entity) {
        if(entity!=null && entity.getInStock()){
            Product product = new Product();
            product.setId(entity.getId());
            product.setProductName(entity.getProductName());
            product.setDescription(entity.getDescription());
            product.setCategory(entity.getCategory());
            product.setPrice(entity.getPrice());
            if(entity.getQuantity()>0){
                product.setInStock(true);
            }else{
                product.setInStock(false);
            }
            product.setQuantity(entity.getQuantity());
            product.setImageUrl(entity.getImageUrl());
            product.setOwnerCompany(entity.getOwnerCompany());
            return product;
        }else {
            return null;
        }
    }
}
