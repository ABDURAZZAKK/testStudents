package com.example.testStudent.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Queston {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String question;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<Answer> answers;

    @OneToOne
    private Answer correct_ans;

}
