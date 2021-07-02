package com.example.testStudent.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;

    @ManyToOne
    private Test test;

    @OneToMany
    @JoinColumn
    private List<Answer> answers;

    @OneToOne
    private Answer correct_ans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getCorrect_ans() {
        return correct_ans;
    }

    public void setCorrect_ans(Answer correct_ans) {
        this.correct_ans = correct_ans;
    }



}
