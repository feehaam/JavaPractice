package com.feeham.playground.controllers;

import com.feeham.playground.models.Accommodation;
import com.feeham.playground.services.interfaces.AccommodationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class AccommodationController {

    private final AccommodationService accommodationService;


    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @QueryMapping
    public Accommodation getAccommodationById(@Argument Integer accommodationId){
        return accommodationService.getById(accommodationId);
    }

    @QueryMapping
    public List<Accommodation> getAccommodationsByType(@Argument String type){
        return accommodationService.getByType(type);
    }

    @MutationMapping
    public Accommodation createAccommodation(@Argument Map<String, Object> input) {
        return accommodationService.create(input);
    }

    @MutationMapping
    public Accommodation updateAccommodation(@Argument Integer accommodationId, @Argument Map<String, Object> input) {
        return accommodationService.update(accommodationId, input);
    }

    @MutationMapping
    public Boolean deleteAccommodation(@Argument Integer accommodationId) {
        return accommodationService.delete(accommodationId);
    }
}
