package com.example.testStudent.models;

import javax.persistence.*;
import java.util.List;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    @OneToMany
    private List<Test> passedTests;






}
