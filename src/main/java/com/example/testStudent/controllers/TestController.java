package com.example.testStudent.controllers;

import com.example.testStudent.Services.TestService;
import com.example.testStudent.exceptions.NotFoundException;
import com.example.testStudent.models.AnswersFromStudent;
import com.example.testStudent.models.Queston;
import com.example.testStudent.models.Test;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Queston> getOneTest(@PathVariable Test test){
        return testService.findOneTest(test);
    }

    @PostMapping
    public HashMap createAnswerFromStudent(@RequestBody AnswersFromStudent answersFromStudent){
        HashMap response = new HashMap<>();
        answersFromStudent.setSumOfPoints(testService.scoring(answersFromStudent));

        response.put("correct_list", testService.correctList(answersFromStudent));
        response.putAll(testService.correctOfAll(testService.answerFstSave(answersFromStudent)));
        return  response;

    }

}

