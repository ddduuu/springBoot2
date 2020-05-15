package com.example.controller;

import com.example.dao.StudentRep;
import com.example.pojo.Student;
import com.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StrdentController {
//    private StudentRep studentRep;
    private StudentService studentService;

    @GetMapping(value = "/getAllStudent")
    public List getAllStudent() {
        return studentService.getAllStudent();
    }

    @PostMapping(value = "/saveStudent")
    //@RequestParam String name, @RequestParam String age, @RequestParam String sex
    public Student saveStudent(Student stu) {
        return studentService.saveStudent(stu);
    }


}
