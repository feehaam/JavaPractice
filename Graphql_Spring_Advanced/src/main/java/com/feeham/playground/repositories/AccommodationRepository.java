package com.feeham.playground.repositories;

import com.feeham.playground.models.Accommodation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccommodationRepository extends MongoRepository<Accommodation, Integer> {
}
