package com.tlsrk.apps.ecom.dto;

import com.tlsrk.apps.ecom.entities.OrderEntity;
import com.tlsrk.apps.ecom.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem{
    private ProductEntity product;
    private Integer quantity;
    private BigDecimal price;

}
