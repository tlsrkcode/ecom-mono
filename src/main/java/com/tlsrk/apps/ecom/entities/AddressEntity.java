package com.tlsrk.apps.ecom.entities;

import com.tlsrk.apps.ecom.enums.AddressType;
import com.tlsrk.apps.ecom.enums.EntryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AddressEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private AddressType addressType;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
    private EntryType createdBy = EntryType.APPLICATION;
    private EntryType modifiedBy = EntryType.APPLICATION;
}
