package com.example.service.impl;

import com.example.dao.StudentRep;
import com.example.pojo.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSertviceImpl implements StudentService {
    @Autowired
    private StudentRep studentRep;

    @Override
    public List<Student> getAllStudent() {
        return studentRep.findAll();
    }

    @Override
    public Student saveStudent(Student stu) {
        return studentRep.save(stu);
    }
}
