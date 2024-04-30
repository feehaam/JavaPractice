package com.feeham.playground.controllers;

import com.feeham.playground.models.Student;
import com.feeham.playground.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping
    ResponseEntity<List<Student>> getAllStudents(){
        var result = studentRepository.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    ResponseEntity<String> addNewStudent(@RequestBody Student student){
        studentRepository.save(student);
        return ResponseEntity.ok("Saved successfully!");
    }
}
