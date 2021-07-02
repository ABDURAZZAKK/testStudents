package com.example.testStudent.repo;

import com.example.testStudent.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
