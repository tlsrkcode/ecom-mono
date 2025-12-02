package com.tlsrk.apps.ecom.entities;

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
@Entity(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;
    private String productName;
    private BigDecimal price;
    private String description;
    private String category;
    private Boolean inStock;
    private Integer quantity;
    private String imageUrl;
    private String ownerCompany;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
}
