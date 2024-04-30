package com.feeham.playground.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Document(collection = "courses")
public class Course {
    @Id
    private Long id;
    private String title;
    private String tutorName;
    private List<Student> enrolledStudents;
}
