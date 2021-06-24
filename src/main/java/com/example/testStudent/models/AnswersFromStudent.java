package com.example.testStudent.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class AnswersFromStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Student student;

    @OneToOne
    private Test test;

    @ManyToMany
    private List<Queston> questions;

    @ManyToMany
    private List<Answer> answers;

    private Double sumOfPoints;

    public AnswersFromStudent() {
    }

    public AnswersFromStudent(Integer id, Student student, Test test, List<Queston> questions, List<Answer> answers, Double sumOfPoints) {
        this.id = id;
        this.student = student;
        this.test = test;
        this.questions = questions;
        this.answers = answers;
        this.sumOfPoints = sumOfPoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Queston> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Queston> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Double getSumOfPoints() {
        return sumOfPoints;
    }

    public void setSumOfPoints(Double sumOfPoints) {
        this.sumOfPoints = sumOfPoints;
    }
}
