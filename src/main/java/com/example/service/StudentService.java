package com.example.service;

import com.example.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    Student saveStudent(Student stu);

}
