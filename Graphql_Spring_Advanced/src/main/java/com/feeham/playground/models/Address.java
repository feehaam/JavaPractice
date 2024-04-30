package com.feeham.playground.models;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Address {
//    private Integer addressId;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + postalCode + ", " + country;
    }
}
