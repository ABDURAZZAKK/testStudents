package com.example.testStudent.controllers;

import com.example.testStudent.exceptions.NotFoundException;
import com.example.testStudent.models.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tests")
public class TestController {
//    public List<Map<Integer,List<List<Map<String,List<String>>>>>> tests = new ArrayList<Map<Integer,List<List<Map<String,List<String>>>>>>(){{
//        add new HashMap<Integer,List<List<Map<String,List<String>>>>>() {{put(1,
//            new ArrayList<List<List<Map<String,List<String>>>>(){{}});}} }};
    public List<Map<Integer,Map<String,List<String>>>> tests = new ArrayList<Map<Integer,Map<String,List<String>>>>(){{
        add(new HashMap<Integer,Map<String,List<String>>>(){{ put(1,new HashMap<>(){{
            put("2+2=?",new ArrayList<String>(){{
                add("2");
                add("1");
                add("3");
                add("4");
            }});

        }}); }});
        add(new HashMap<Integer,Map<String,List<String>>>(){{ put(1,new HashMap<>(){{
                put("Какой цвет у картины, 'Черный квадрат' Малевича",new ArrayList<String>(){{
                    add("Красный");
                    add("Черный");
                    add("Синий");
                    add("Зеленый");
                }});

        }});  }});
}};



    @GetMapping
    public List<Map<Integer,Map<String,List<String>>>> list(){
        return tests;
    }
    @GetMapping("{id}")
    public Map<Integer,Map<String,List<String>>> getOne(@PathVariable Integer id){
        return tests.stream()
                .filter(test -> test.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

}
//[{"1":{"2+2=?":["2","1","3","4"]}}]
// [{ 1:{["":["",""]] }]
// [{1:Test},{2:Test}]
//Test = {"?":["","r",""]}
