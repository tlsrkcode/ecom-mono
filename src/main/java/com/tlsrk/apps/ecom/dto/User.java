package com.tlsrk.apps.ecom.dto;

import com.tlsrk.apps.ecom.entities.AddressEntity;
import com.tlsrk.apps.ecom.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
    private Address address;
}
