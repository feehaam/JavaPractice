package com.feeham.playground.controllers;

import com.feeham.playground.models.Course;
import com.feeham.playground.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;

    @GetMapping
    ResponseEntity<List<Course>> getAllStudents(){
        var result = courseRepository.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    ResponseEntity<String> addNewStudent(@RequestBody Course course){
        courseRepository.save(course);
        return ResponseEntity.ok("Saved successfully!");
    }
}
