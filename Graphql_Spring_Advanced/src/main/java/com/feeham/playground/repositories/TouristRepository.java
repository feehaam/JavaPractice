package com.feeham.playground.repositories;

import com.feeham.playground.models.Tourist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TouristRepository extends MongoRepository<Tourist, Integer> {
}
