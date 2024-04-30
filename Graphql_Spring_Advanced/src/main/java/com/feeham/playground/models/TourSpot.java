package com.feeham.playground.models;

import lombok.*;

import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class TourSpot {
    private Integer tourSpotId;
    private String tourSpotName;
    private String description;
    private Set<String> photos;
    private Address address;
    private GeoLocation geoLocation;
    private Set<Accommodation> nearbyHotels;
    private Double averageRating;
    private Integer totalRatingCount;
    private Set<Rating> ratings;
}
