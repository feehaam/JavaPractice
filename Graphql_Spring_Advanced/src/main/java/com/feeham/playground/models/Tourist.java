package com.feeham.playground.models;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Tourist {
    private Integer touristId;
    private String firstName;
    private String lastName;
    private Address currentAddress;
    private Address birthPlace;
}
