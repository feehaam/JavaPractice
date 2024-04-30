package com.feeham.playground.controllers;

import com.feeham.playground.models.TourSpot;
import com.feeham.playground.services.interfaces.TourSpotService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class TourSpotController {

    private final TourSpotService tourSpotService;

    public TourSpotController(TourSpotService tourSpotService) {
        this.tourSpotService = tourSpotService;
    }

    @QueryMapping
    public TourSpot getTourSpotById(@Argument Integer tourSpotId){
        return tourSpotService.getTourSpotById(tourSpotId);
    }

    @QueryMapping
    public List<TourSpot> getTourSpotsByRating(@Argument Integer minRating, @Argument Integer maxRating){
        return tourSpotService.getTourSpotsByRating(minRating, maxRating);
    }

    @MutationMapping
    public TourSpot createTourSpot(@Argument Map<String, Object> input) {
        return tourSpotService.create(input);
    }

    @MutationMapping
    public TourSpot updateTourSpot(@Argument Integer tourSpotId, @Argument Map<String, Object> input) {
        return tourSpotService.update(tourSpotId, input);
    }

    @MutationMapping
    public Boolean deleteTourSpot(@Argument Integer tourSpotId) {
        return tourSpotService.delete(tourSpotId);
    }
}
