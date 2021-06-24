package com.example.testStudent.Services;

import com.example.testStudent.models.Answer;
import com.example.testStudent.models.AnswersFromStudent;
import com.example.testStudent.models.Queston;
import com.example.testStudent.models.Test;
import com.example.testStudent.repo.AnswerFromStudentRepo;
import com.example.testStudent.repo.QuestionRepo;
import com.example.testStudent.repo.TestRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class TestService {
    private final TestRepo testRepo;
    private final QuestionRepo questionRepo;
    private final AnswerFromStudentRepo answerFromStudentRepo;

    public TestService(TestRepo testRepo, QuestionRepo questionRepo, AnswerFromStudentRepo answerFromStudentRepo) {
        this.testRepo = testRepo;
        this.questionRepo = questionRepo;
        this.answerFromStudentRepo = answerFromStudentRepo;
    }

    public List<Test> findAllTests(){
        return testRepo.findAll();
    }

    public List<Queston> findOneTest(Test test){
        return questionRepo.findByTest(test);
    }

    public Double scoring(AnswersFromStudent answersFromStudent){
        Double result = 0d;

        Iterator<Answer> answers = answersFromStudent.getAnswers().listIterator();
        for(Queston quest : answersFromStudent.getQuestions()){
            if(questionRepo.findById(quest.getId()).equals(answers.next().getId())){
                result++;
            }
        }
        return result;
    }
    public List<Answer> correctList(AnswersFromStudent answersFromStudent){
        List<Answer> result = new ArrayList<>();
        Answer next;

        Iterator<Answer> answers = answersFromStudent.getAnswers().listIterator();
        for(Queston quest : answersFromStudent.getQuestions()){
            next = answers.next();
            if(questionRepo.findById(quest.getId()).equals(next.getId())){
                result.add(next);
            }
        }
        return result;
    }

    public HashMap correctOfAll(AnswersFromStudent answersFromStudent){
        HashMap result = new HashMap<>();
        result.put("all_quertions",questionRepo.findByTest(answersFromStudent.getTest()).size());
        result.put("correct_answers",answersFromStudent.getSumOfPoints());
        return result;
    }

    public AnswersFromStudent answerFstSave(AnswersFromStudent answersFromStudent){
        return answerFromStudentRepo.save(answersFromStudent);
    }

}
