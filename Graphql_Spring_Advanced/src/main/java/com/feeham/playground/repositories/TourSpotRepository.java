package com.feeham.playground.repositories;

import com.feeham.playground.models.TourSpot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TourSpotRepository extends MongoRepository<TourSpot, Integer> {
}
