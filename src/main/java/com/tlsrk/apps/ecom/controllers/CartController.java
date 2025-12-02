package com.tlsrk.apps.ecom.controllers;

import com.tlsrk.apps.ecom.dto.CartItem;
import com.tlsrk.apps.ecom.dto.User;
import com.tlsrk.apps.ecom.services.CartService;
import com.tlsrk.apps.ecom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    ResponseEntity<List<CartItem>> responseList = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    ResponseEntity<CartItem> response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    @PostMapping
    public ResponseEntity<String> getAllCartItems(@RequestHeader("X-User-ID") String userId, @RequestBody CartItem cartItem) {
        CartItem savedItem = cartService.addToCart(userId, cartItem);
        if(savedItem != null) {
            return new ResponseEntity("Items Added To Cart Successfully", HttpStatus.CREATED);
        }else{
            return new ResponseEntity("Error Occurred While Adding Items To Cart. Please Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> removeCartItems(@RequestHeader("X-User-ID") String userId,@PathVariable Long productId) {
       boolean isDeleted = cartService.removeFromCart(userId, productId);
       if(isDeleted){
           return new ResponseEntity("Product "+productId+" Deleted Successfully", HttpStatus.OK);
       }else{
           return new ResponseEntity("Error Occurred While Deleting Items. lease Try Again", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-User-ID") String userId) {
        List<CartItem> cartItemList = cartService.getCartItems(userId);
        return new ResponseEntity(cartItemList, HttpStatus.OK);
    }
}
