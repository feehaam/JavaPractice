package com.feeham.playground.services.implementations;

import com.feeham.playground.exceptions.CustomException;
import com.feeham.playground.models.Accommodation;
import com.feeham.playground.models.Address;
import com.feeham.playground.models.Facilities;
import com.feeham.playground.models.GeoLocation;
import com.feeham.playground.repositories.AccommodationRepository;
import com.feeham.playground.services.interfaces.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Override
    public Accommodation getById(Integer accommodationId) {
        Optional<Accommodation> result = accommodationRepository.findById(accommodationId);
        if(result.isEmpty()){
            throw new CustomException("Accommodation with ID "+ accommodationId
                    + " was not found!", HttpStatus.NOT_FOUND);
        }
        return result.get();
    }

    @Override
    public List<Accommodation> getByType(String type) {
        return accommodationRepository.findAll().stream()
                .filter(acc -> acc.getType().equalsIgnoreCase(type))
                .toList();
    }

    private Accommodation getObjectFromRequestMap(Map<String, Object> mappedObject){
        return Accommodation.builder()
                .name(read(mappedObject, ACCOMMODATION_NAME, String.class, ERROR_ACCOMMODATION_NAME_MISSING))
                .type(read(mappedObject, ACCOMMODATION_TYPE, String.class, ERROR_ACCOMMODATION_TYPE_MISSING))
                .address(read(mappedObject, ACCOMMODATION_ADDRESS, Address.class, ERROR_ACCOMMODATION_ADDRESS_MISSING))
                .description(read(mappedObject, ACCOMMODATION_DESCRIPTION, String.class, ERROR_ACCOMMODATION_DESCRIPTION_INVALID))
                .facilities(read(mappedObject, ACCOMMODATION_FACILITIES, Facilities.class, ERROR_ACCOMMODATION_FACILITIES_MISSING))
                .geoLocation(read(mappedObject, ACCOMMODATION_GEOLOCATION, GeoLocation.class, ERROR_ACCOMMODATION_GEOLOCATION_MISSING))
                .photos(read(mappedObject, ACCOMMODATION_PHOTOS, Set.class, ERROR_ACCOMMODATION_PHOTOS_MISSING))
                .build();
    }

    @Override
    public Accommodation create(Map<String, Object> mappedObject) {
        Accommodation accommodation = getObjectFromRequestMap(mappedObject);
        accommodation.setAverageRating(0.0);
        accommodation.setTotalRatingCount(0);
        accommodationRepository.save(accommodation);
        return accommodation;
    }

    @Override
    public Accommodation update(Integer accId, Map<String, Object> mappedObject) {
        Accommodation existing = getById(accId);
        Accommodation accommodation = getObjectFromRequestMap(mappedObject);
        existing.setName(accommodation.getName());
        existing.setType(accommodation.getType());
        existing.setAddress(accommodation.getAddress());
        existing.setFacilities(accommodation.getFacilities());
        existing.setGeoLocation(accommodation.getGeoLocation());
        existing.setPhotos(accommodation.getPhotos());
        existing.setRatings(accommodation.getRatings());
        existing.setDescription(accommodation.getDescription());
        accommodationRepository.save(existing);
        return existing;
    }

    @Override
    public Boolean delete(Integer accId) {
        Accommodation existing = getById(accId);
        accommodationRepository.delete(existing);
        return true;
    }

    @Override
    public Set<Accommodation> nearbyAccommodations(GeoLocation geoLocation) {
        return Set.copyOf(accommodationRepository.findAll().stream().filter(accommodation -> {
            return isClose(geoLocation.getLatitude(), accommodation.getGeoLocation().getLatitude()) &&
                    isClose(geoLocation.getLongitude(), accommodation.getGeoLocation().getLongitude());
        }).toList());
    }

    private boolean isClose(Double positionA, Double positionB){
        return Math.abs(positionA - positionB) <= GEO_POSITION_CLOSE_IN_METER;
    }
}
