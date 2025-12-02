package com.tlsrk.apps.ecom.dto;

import com.tlsrk.apps.ecom.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private AddressType addressType;
}
