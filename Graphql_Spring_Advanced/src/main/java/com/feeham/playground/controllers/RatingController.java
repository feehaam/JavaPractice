package com.feeham.playground.controllers;

import com.feeham.playground.models.Rating;
import com.feeham.playground.services.interfaces.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller @AllArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @MutationMapping
    public Rating createRating(@Argument Map<String, Object> input){
        return ratingService.create(input);
    }

    @MutationMapping
    public Rating updateRating(@Argument Integer ratingId, @Argument Map<String, Object> input){
        return ratingService.update(ratingId, input);
    }

    @MutationMapping
    public Boolean deleteRating(@Argument Integer ratingId){
        return ratingService.delete(ratingId);
    }

}
