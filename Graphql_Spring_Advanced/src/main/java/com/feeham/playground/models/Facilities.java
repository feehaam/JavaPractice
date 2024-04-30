package com.feeham.playground.models;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Facilities {
    private Integer facId;
    private Double rentPerNight;
    private Boolean foodIncluded;
    private Double discount;
    private String offers;
}
