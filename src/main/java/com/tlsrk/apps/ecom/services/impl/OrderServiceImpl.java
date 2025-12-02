package com.tlsrk.apps.ecom.services.impl;

import com.tlsrk.apps.ecom.dto.Order;
import com.tlsrk.apps.ecom.dto.OrderedItem;
import com.tlsrk.apps.ecom.entities.*;
import com.tlsrk.apps.ecom.enums.OrderStatus;
import com.tlsrk.apps.ecom.repo.*;
import com.tlsrk.apps.ecom.services.CartService;
import com.tlsrk.apps.ecom.services.OrderService;
import com.tlsrk.apps.ecom.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    OrdersRepository ordersRepo;
    @Autowired
    OrderedItemRepository orderedItemRepo;
    @Autowired
    CartRepository cartRepo;
    @Autowired
    ProductRepository  productRepo;
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @Override
    @Transactional
    public Order placeOrder(String userId) {
        HashMap<Long, Integer> productMap = new HashMap<>();
        //validate user
        UserEntity user = validateUser(userId);

        //get cart Items and prepare Order entity and calculate tolal order price
        List<CartEntity> cartEntityList = cartRepo.findByUser(user);

        BigDecimal totalOrderPrice = BigDecimal.ZERO;
        List<OrderedItemEntity> orderedItemsList = new ArrayList<>();
        cartEntityList.stream().forEach(cartItem -> {
            totalOrderPrice.add(cartItem.getTotalPrice());
            OrderedItemEntity orderedItemEntity = new OrderedItemEntity();
            orderedItemEntity.setPrice(cartItem.getTotalPrice());
            orderedItemEntity.setQuantity(cartItem.getQuantity());
            orderedItemEntity.setProduct(cartItem.getProduct());
            orderedItemsList.add(orderedItemEntity);
            productMap.put(cartItem.getProduct().getId(), cartItem.getQuantity());
        });


        System.out.println("Total price is " + totalOrderPrice);

        //place order
       OrderEntity orderEntity = new OrderEntity();
       orderEntity.setTotalPrice(totalOrderPrice);
       orderEntity.setOrderedItems(orderedItemsList);
       orderEntity.setOrderStatus(OrderStatus.PENDING);
       orderEntity.setUser(user);
                                                                                                                     
        List<OrderedItemEntity> itemsList = cartEntityList.stream().map( cartItem -> (
                new OrderedItemEntity(
                       null,                                                                                         
                       cartItem.getProduct(),                                                                        
                       cartItem.getQuantity(),
                       cartItem.getTotalPrice(),
                       orderEntity))).collect(Collectors.toList());
        orderEntity.setOrderedItems(itemsList);

       OrderEntity orderPlaced = ordersRepo.save(orderEntity);
      // orderPlaced = ordersRepo.save(orderEntity);
        /**
       List<OrderedItemEntity> itemList = new ArrayList<OrderedItemEntity>();
       orderedItemsList.forEach(orderedItem -> {
           OrderedItemEntity item = new OrderedItemEntity();
            item.setProduct(orderedItem.getProduct());
            item.setQuantity(orderedItem.getQuantity());
            item.setPrice(orderedItem.getPrice());
            item.setOrders(orderEntity);
           itemList.add(item);
        });
         **/

        //clear cart items
        cartRepo.deleteByUser(user);

        //update product quantity in Product Table
        List<ProductEntity> updatedProductList = new ArrayList<>();
        productMap.forEach((productId, quantity) -> {
            ProductEntity product = productRepo.findById(productId).get();
            int newQuantity = product.getQuantity() - quantity;
            product.setQuantity(newQuantity);
            updatedProductList.add(product);
        });
        productRepo.saveAll(updatedProductList);

        return mapOrderEntityToOrder(orderEntity);
    }

    private UserEntity validateUser(String userId) {
       return userRepo.findById(Long.parseLong(userId)).get();
    }

    private Order mapOrderEntityToOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.CONFIRMNED);
        order.setTotalPrice(orderEntity.getTotalPrice());
        order.setUserId(orderEntity.getUser().getId());
       // order.setOrderedItemsList();
        return order;
    }

}
