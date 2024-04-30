package com.feeham.playground.repositories;

import com.feeham.playground.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, Long> {
}
