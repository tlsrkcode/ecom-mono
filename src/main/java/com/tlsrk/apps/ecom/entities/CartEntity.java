package com.tlsrk.apps.ecom.entities;

import com.tlsrk.apps.ecom.dto.Product;
import com.tlsrk.apps.ecom.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "CART")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID", nullable = false)
    private ProductEntity product;
    private Integer quantity;
    private BigDecimal totalPrice;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
