package com.feeham.playground.models;

import lombok.*;

import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Accommodation {
    private Integer accommodationId;
    private String name;
    private String type;
    private Address address;
    private String description;
    private Facilities facilities;
    private GeoLocation geoLocation;
    private Set<String> photos;
    private Double averageRating;
    private Integer totalRatingCount;
    private Set<Rating> ratings;
}
