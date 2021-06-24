package com.example.testStudent.repo;

import com.example.testStudent.models.AnswersFromStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerFromStudentRepo extends JpaRepository<AnswersFromStudent, Integer> {
}
