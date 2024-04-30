package com.feeham.playground.repositories;

import com.feeham.playground.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, Integer> {
}
