package com.feeham.playground.controllers;

import com.feeham.playground.models.Course;
import com.feeham.playground.models.Student;
import com.feeham.playground.repositories.CourseRepository;
import com.feeham.playground.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/enroll")
@RequiredArgsConstructor
public class EnrollController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<?> enrollNewStudentToACourse(@RequestParam Long courseId, @RequestParam Long studentId){
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Student> student = studentRepository.findById(studentId);
        if(course.isEmpty()) return new ResponseEntity<>("Course doesn't exist", HttpStatus.BAD_REQUEST);
        if(student.isEmpty()) return new ResponseEntity<>("Student doesn't exist", HttpStatus.BAD_REQUEST);
        Course c = course.get();
        c.getEnrolledStudents().add(student.get());
        courseRepository.save(c);
        return ResponseEntity.ok("Enrolled!");
    }
}
