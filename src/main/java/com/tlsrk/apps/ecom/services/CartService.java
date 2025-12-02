package com.tlsrk.apps.ecom.services;

import com.tlsrk.apps.ecom.dto.CartItem;
import com.tlsrk.apps.ecom.dto.Product;

import java.util.List;


public interface CartService {
    CartItem addToCart(String userId, CartItem cartItem);
    boolean removeFromCart(String userId, Long productId);
    List<CartItem> getCartItems(String userId);
}
