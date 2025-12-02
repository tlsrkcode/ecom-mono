package com.tlsrk.apps.ecom.dto;

import com.tlsrk.apps.ecom.entities.UserEntity;
import com.tlsrk.apps.ecom.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Order {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
    private List<OrderedItem> orderedItemsList;
}
