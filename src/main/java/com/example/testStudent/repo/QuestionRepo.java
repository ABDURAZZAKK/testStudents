package com.example.testStudent.repo;

import com.example.testStudent.models.Queston;
import com.example.testStudent.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Queston, Integer> {
    public List<Queston> findByTest(Test test);
}
