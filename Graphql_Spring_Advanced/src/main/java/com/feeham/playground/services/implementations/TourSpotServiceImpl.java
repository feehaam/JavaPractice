package com.feeham.playground.services.implementations;

import com.feeham.playground.exceptions.CustomException;
import com.feeham.playground.models.TourSpot;
import com.feeham.playground.repositories.TourSpotRepository;
import com.feeham.playground.services.interfaces.AccommodationService;
import com.feeham.playground.services.interfaces.TourSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TourSpotServiceImpl implements TourSpotService {

    private final TourSpotRepository tourSpotRepository;
    private final AccommodationService accommodationService;

    @Override
    public TourSpot getTourSpotById(Integer tourSpotId) {
        TourSpot tourSpot = getTourSpot(tourSpotId);
        setNearbyAccommodations(tourSpot);
        return tourSpot;
    }

    @Override
    public List<TourSpot> getTourSpotsByRating(Integer min, Integer max) {
        return tourSpotRepository.findAll().stream()
                .filter(tourSpot ->
                        tourSpot.getAverageRating() >= min &&
                        tourSpot.getAverageRating() <= max)
                .toList();
    }

    @Override
    public TourSpot create(Map<String, Object> input) {
        TourSpot tourSpot = mapToObject(input, TourSpot.class);
        tourSpot.setTourSpotId(0);
        tourSpot.setRatings(new HashSet<>());
        tourSpot.setAverageRating(0.0);
        tourSpot.setTotalRatingCount(0);
        tourSpot.setNearbyHotels(new HashSet<>());
        tourSpotRepository.save(tourSpot);
        return tourSpot;
    }

    @Override
    public TourSpot update(Integer tourSpotId, Map<String, Object> input) {
        TourSpot tourSpotUpdate = mapToObject(input, TourSpot.class);
        TourSpot tourSpot = getTourSpot(tourSpotId);
        tourSpot.setTourSpotName(tourSpotUpdate.getTourSpotName());
        tourSpot.setAddress(tourSpotUpdate.getAddress());
        tourSpot.setPhotos(tourSpotUpdate.getPhotos());
        tourSpot.setGeoLocation(tourSpotUpdate.getGeoLocation());
        tourSpotRepository.save(tourSpot);
        return tourSpot;
    }

    @Override
    public Boolean delete(Integer tourSpotId) {
        TourSpot tourSpot = getTourSpot(tourSpotId);
        tourSpotRepository.delete(tourSpot);
        return true;
    }

    private TourSpot getTourSpot(Integer tourSpotId){
        Optional<TourSpot> tourSpotOp = tourSpotRepository.findById(tourSpotId);
        if(tourSpotOp.isEmpty()) {
            throw new CustomException("Tour spot with ID " + tourSpotId + " doesn't exist.", HttpStatus.NOT_FOUND);
        }
        return tourSpotOp.get();
    }

    private void setNearbyAccommodations(TourSpot tourSpot){
        tourSpot.setNearbyHotels(accommodationService
                .nearbyAccommodations(tourSpot.getGeoLocation()));
    }
}
