package com.tlsrk.apps.ecom.dto;

import com.tlsrk.apps.ecom.entities.ProductEntity;
import com.tlsrk.apps.ecom.entities.UserEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CartItem {
    private Long productId;
    private Long userId;
    private Product productDetails;
    private Integer quantity;
    private BigDecimal totalPrice;
}
