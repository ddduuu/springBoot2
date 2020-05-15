package com.example.controller;

import com.example.pojo.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {
//    @Value("${student.name}")
//    private String name;
//    @Value("${student.age}")
//    private String age;
//    @Value("${student.sex}")
//    private String sex;
    @Resource
    private Student student;

    @GetMapping(value = "/getStr")
    public String getStr(){
        String s = "测试"+student.getName();
        System.out.println("内容："+student.getContent());
        return s;
    }

    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    @GetMapping(value = {"/hello","/hi"})
    public String say(){
        return "hi you!!!,年龄："+student.getAge()+",性别："+student.getSex();
    }


}
