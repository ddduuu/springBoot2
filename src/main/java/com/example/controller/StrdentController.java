package com.example.controller;

import com.example.dao.StudentRep;
import com.example.pojo.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StrdentController {
    @Autowired
//    private StudentRep studentRep;
    private StudentService studentService;

    @GetMapping(value = "/getAllStudent")//@GetMapping等介于@RequestMapping和method=RequestMethod.GET
    public List getAllStudent() {
        return studentService.getAllStudent();
    }

    @PostMapping(value = "/saveStudent")//@PostMapping等介于@RequestMapping和method=RequestMethod.POST
    //@RequestParam String name, @RequestParam String age, @RequestParam String sex
    public Student saveStudent(Student stu) {
        return studentService.saveStudent(stu);
    }


}