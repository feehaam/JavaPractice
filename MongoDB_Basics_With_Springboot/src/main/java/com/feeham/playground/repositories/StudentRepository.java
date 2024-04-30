package com.feeham.playground.repositories;

import com.feeham.playground.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Long> {
}
