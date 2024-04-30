package com.feeham.playground.controllers;

import com.feeham.playground.models.Address;
import com.feeham.playground.models.Tourist;
import com.feeham.playground.services.interfaces.TouristService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class TouristController {

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @QueryMapping
    public Tourist getTouristById(@Argument Integer touristId){
        return touristService.getTouristsById(touristId);
    }

    @QueryMapping
    public List<Tourist> getTouristByName(@Argument String name){
        return touristService.getTouristsByName(name);
    }

    @MutationMapping
    public Tourist createTourist(@Argument Map<String, Object> input){
        return touristService.create(input);
    }

    @MutationMapping
    public Tourist updateTourist(@Argument Integer touristId, @Argument Map<String, Object> input){
        return touristService.update(touristId, input);
    }

    @MutationMapping
    public Boolean deleteTourist(@Argument Integer touristId){
        return touristService.delete(touristId);
    }

}
