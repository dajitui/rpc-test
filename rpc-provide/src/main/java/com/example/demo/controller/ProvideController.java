package com.example.demo.controller;

import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvideController {

    @RequestMapping(value = "/provide",method = RequestMethod.GET)
    public Student get(){
        return new Student("大鸡腿",2);
    }

}
