package com.example.testStudent.repo;

import com.example.testStudent.models.Question;
import com.example.testStudent.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
     List<Question> findByTest(Test test);
}
