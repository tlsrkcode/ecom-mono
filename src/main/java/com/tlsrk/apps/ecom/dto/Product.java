package com.tlsrk.apps.ecom.dto;

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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Product{
    private Long id;
    private String productName;
    private BigDecimal price;
    private String description;
    private String category;
    private Boolean inStock;
    private Integer quantity;
    private String imageUrl;
    private String ownerCompany;
}
