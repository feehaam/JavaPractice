package com.feeham.playground.constants;

public interface ErrorConstants {
    // Accommodations errors
    public final String ERROR_ACCOMMODATION_NAME_MISSING = "Accommodation name is required.";
    public final String ERROR_ACCOMMODATION_TYPE_MISSING = "Accommodation type is required.";
    public final String ERROR_ACCOMMODATION_ADDRESS_MISSING = "Accommodation address is required.";
    public final String ERROR_ACCOMMODATION_DESCRIPTION_INVALID = "Invalid accommodation description.";
    public final String ERROR_ACCOMMODATION_FACILITIES_MISSING = "Accommodation facilities are required.";
    public final String ERROR_ACCOMMODATION_GEOLOCATION_MISSING = "Accommodation geolocation is required.";
    public final String ERROR_ACCOMMODATION_PHOTOS_MISSING = "Accommodation photos are required.";

    // Geo errors
    public final String ERROR_GEO_LOCATION_LATITUDE_MISSING = "Latitude is required.";
    public final String ERROR_GEO_LOCATION_LONGITUDE_MISSING = "Longitude is required.";

    // Address errors
    public final String ERROR_ADDRESS_STREET_MISSING = "Street is required.";
    public final String ERROR_ADDRESS_CITY_MISSING = "City is required.";
    public final String ERROR_ADDRESS_STATE_MISSING = "State is required.";
    public final String ERROR_ADDRESS_POSTAL_CODE_MISSING = "Postal code is required.";
    public final String ERROR_ADDRESS_COUNTRY_MISSING = "Country is required.";

    // Rating errors
    public final String ERROR_RATING_RATE_MISSING = "Rating rate is required.";
    public final String ERROR_RATING_COMMENT_EMPTY = "Comment body cannot be empty or missing for a rating.";
    public final String ERROR_RATING_RATED_BY_MISSING = "Rated by information is required.";

    // Tourist errors
    public final String ERROR_TOURIST_FIRST_NAME_MISSING = "Tourist first name is required.";
    public final String ERROR_TOURIST_LAST_NAME_MISSING = "Tourist last name is required.";
    public final String ERROR_TOURIST_CURRENT_ADDRESS_MISSING = "Tourist current address is required.";
    public final String ERROR_TOURIST_BIRTH_PLACE_MISSING = "Tourist birth place is required.";

    // TourSpot errors
    public final String ERROR_TOUR_SPOT_NAME_MISSING = "Tour spot must have a spot name.";
    public final String ERROR_TOUR_SPOT_DESCRIPTION_INVALID = "Invalid tour spot description.";
    public final String ERROR_TOUR_SPOT_PHOTOS_MISSING = "Tour spot photos are required.";
}
