package com.tlsrk.apps.ecom.controllers;

import com.tlsrk.apps.ecom.dto.Order;
import com.tlsrk.apps.ecom.enums.OrderStatus;
import com.tlsrk.apps.ecom.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestHeader("X-User-ID") String userId) {
        Order orderPlaced = orderService.placeOrder(userId);
        if(!orderPlaced.getOrderStatus().equals(OrderStatus.CONFIRMNED)){
            return new ResponseEntity<>("Order Couldnot be placed. Please Try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>("Order Placed Successfullu, OrderID "+orderPlaced.getOrderId(), HttpStatus.CREATED);
        }

    }
}
