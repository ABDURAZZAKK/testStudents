package com.example.testStudent.controllers;


import com.example.testStudent.models.AnswersFromStudent;
import com.example.testStudent.models.Student;
import com.example.testStudent.repo.StudentRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }
}