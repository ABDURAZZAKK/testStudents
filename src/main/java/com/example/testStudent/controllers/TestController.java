package com.example.testStudent.controllers;

import com.example.testStudent.Services.TestService;
import com.example.testStudent.models.AnswersFromStudent;
import com.example.testStudent.models.Question;
import com.example.testStudent.models.Test;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping
    public List<Test> listTests(){
        return testService.findAllTests();
    }


    @GetMapping("{id}")
    public List<Question> getOneTest(@PathVariable("id") Test test){
        return testService.findOneTest(test);
    }

    @PostMapping
    public HashMap createAnswerFromStudent(@RequestBody AnswersFromStudent answersFromStudent){
        HashMap response = new HashMap<>();
        answersFromStudent.setSumOfPoints(testService.scoring(answersFromStudent));
        HashMap li = testService.correctOfAll(testService.answerFstSave(answersFromStudent));

        response.put("list_of_correct_answers", testService.correctList(answersFromStudent));
        response.put("num_of_questions_answered", li.get("num_of_questions_answered"));
        response.put("num_of_correct_answers", li.get("num_of_correct_answers"));
        return  response;

    }

}

