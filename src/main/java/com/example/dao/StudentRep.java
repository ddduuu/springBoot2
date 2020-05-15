package com.example.dao;

import com.example.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRep extends JpaRepository<Student,Integer> {


}
