package com.tlsrk.apps.ecom.services;

import com.tlsrk.apps.ecom.dto.Order;

public interface OrderService {
    Order placeOrder(String userId);
}
