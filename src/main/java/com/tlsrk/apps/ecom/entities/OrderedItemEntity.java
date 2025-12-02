package com.tlsrk.apps.ecom.entities;

import com.tlsrk.apps.ecom.dto.Product;
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
@Entity(name = "ORDERED_ITEMS")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderedItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",  referencedColumnName = "product_id", nullable = false)
    private ProductEntity product;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", referencedColumnName = "order_id" , nullable = false)
    private OrderEntity orders;

}
