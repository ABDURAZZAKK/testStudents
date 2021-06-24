package com.example.testStudent.models;

import javax.persistence.*;

@Entity
public class AnswersOfStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Student student;
    @OneToOne
    private Test test;
    @OneToOne
    private Queston question;
    @OneToOne
    private Answer answer;

    private Boolean correctly;
}
