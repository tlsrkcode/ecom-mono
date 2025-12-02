package com.tlsrk.apps.ecom.entities;

import com.tlsrk.apps.ecom.enums.EntryType;
import com.tlsrk.apps.ecom.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole ;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="address_id", referencedColumnName = "address_id")
    private AddressEntity address;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    private EntryType createdBy = EntryType.APPLICATION;
    private EntryType modifiedBy = EntryType.APPLICATION;

}
