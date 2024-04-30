package com.feeham.playground.services.implementations;

import com.feeham.playground.constants.enums.RatingType;
import com.feeham.playground.exceptions.CustomException;
import com.feeham.playground.models.Accommodation;
import com.feeham.playground.models.Rating;
import com.feeham.playground.models.TourSpot;
import com.feeham.playground.models.Tourist;
import com.feeham.playground.repositories.AccommodationRepository;
import com.feeham.playground.repositories.RatingRepository;
import com.feeham.playground.repositories.TourSpotRepository;
import com.feeham.playground.services.interfaces.AccommodationService;
import com.feeham.playground.services.interfaces.RatingService;
import com.feeham.playground.services.interfaces.TourSpotService;
import com.feeham.playground.services.interfaces.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final TouristService touristService;
    private final TourSpotRepository tourSpotRepository;
    private final AccommodationRepository accommodationRepository;

    @Override
    public Rating create(Map<String, Object> input) {
        Rating rating = mapToObject(input, Rating.class);
        rating.setRatingId(0);
        rating.setCreated(LocalDateTime.now());
        Tourist user = touristService.getTouristsById(getUserId());
        setTarget(rating);
        ratingRepository.save(rating);
        return rating;
    }

    @Override
    public Rating update(Integer ratingId, Map<String, Object> input) {
        Rating ratingUpdated = mapToObject(input, Rating.class);
        Rating rating = getRatingAndCheckAccess(ratingId);
        rating.setUpdated(LocalDateTime.now());
        rating.setRate(ratingUpdated.getRate());
        rating.setComment(ratingUpdated.getComment());
        ratingRepository.save(rating);
        return rating;
    }

    @Override
    public Boolean delete(Integer ratingId) {
        Rating rating = getRatingAndCheckAccess(ratingId);
        ratingRepository.delete(rating);
        return true;
    }

    private Rating getRatingAndCheckAccess(Integer ratingId){
        Optional<Rating> ratingOp = ratingRepository.findById(ratingId);
        if(ratingOp.isEmpty()) {
            throw new CustomException("Rating is not found.", HttpStatus.NOT_FOUND);
        }
        Rating rating = ratingOp.get();
        if(!Objects.equals(rating.getRatedBy().getTouristId(), getUserId())){
            throw new CustomException("Access denied! Only rating owner edit or delete.", HttpStatus.BAD_REQUEST);
        }
        return rating;
    }

    private void setTarget(Rating rating) {
        if (rating.getRatingType().equals(RatingType.ACCOMMODATION))
            addRatingToAccommodation(rating);
        else addRatingToTourSpot(rating);
    }

    private void addRatingToAccommodation(Rating rating){
        Accommodation accommodation = getAccommodationById(rating.getRatingId());
        accommodation.getRatings().add(rating);
        accommodationRepository.save(accommodation);
    }

    private void addRatingToTourSpot(Rating rating){
        TourSpot tourSpot = getTourSpotById(rating.getRatingId());
        tourSpot.getRatings().add(rating);
        tourSpotRepository.save(tourSpot);
    }

    private Accommodation getAccommodationById(Integer accommodationId) {
        Optional<Accommodation> result = accommodationRepository.findById(accommodationId);
        if(result.isEmpty()){
            throw new CustomException("Accommodation with ID "+ accommodationId
                    + " was not found!", HttpStatus.NOT_FOUND);
        }
        return result.get();
    }

    private TourSpot getTourSpotById(Integer tourSpotId) {
        Optional<TourSpot> tourSpotOp = tourSpotRepository.findById(tourSpotId);
        if(tourSpotOp.isEmpty()) {
            throw new CustomException("Tour spot with ID " + tourSpotId + " doesn't exist.", HttpStatus.NOT_FOUND);
        }
        return tourSpotOp.get();
    }
}
