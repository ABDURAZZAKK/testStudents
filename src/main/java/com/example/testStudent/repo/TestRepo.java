package com.example.testStudent.repo;

import com.example.testStudent.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test, Integer> {
}
