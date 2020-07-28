package com.example.controller;

import com.example.pojo.Student;
import com.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller     //等同于同时加上了@Controller和@ResponseBody
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
    @ResponseBody
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
    /*
    * 测试Thymeleaf传值
    * */
    @GetMapping(value = "/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("userid","123456");
        model.addAttribute("userName","刘宇轩");
        model.addAttribute("userAddress","<span style='color:red;'>山西省临汾市</span>");
        return "testThymeleaf";
    }
    /*
    * 对象传参
    * */
    @GetMapping(value = "/member_show")
    public String memberShow(Model model){
        User u = new User();
        u.setUserId(100L);
        u.setUserName("周立伟");
        u.setAge(18);
        u.setBirthday(new Date());
        u.setSalary(1000.22);
        model.addAttribute("member",u);
        return "member_show";
    }
    /*
    * 测试赋值显示
    * */
    @GetMapping(value = "/userSet")
    public String set(Model model) {
        Set<String> allNames = new HashSet<String>() ;
        List<Integer> allIds = new ArrayList<Integer>() ;
        for (int x = 0 ; x < 5 ; x ++) {
            allNames.add("boot-" + x) ;
            allIds.add(x) ;
        }
        model.addAttribute("names", allNames) ;
        model.addAttribute("ids", allIds) ;
        model.addAttribute("mydate", new java.util.Date()) ;
        return "user_set" ;
    }
    /*
    * 内置对象传参
    * */
    @GetMapping(value = "/inner")
    public String inner(HttpServletRequest request, Model model) {
        request.setAttribute("requestMessage", "springboot-request");
        request.getSession().setAttribute("sessionMessage", "springboot-session");
        request.getServletContext().setAttribute("applicationMessage",
                "springboot-application");
        model.addAttribute("url", "www.baidu.cn");
        request.setAttribute("url2",
                "<span style='color:red'>www.baidu.cn</span>");
        return "show_inner";
    }
    /*
    * map赋值
    * */
    @GetMapping(value = "/user/map")
    public String userMap(Model model){
        Map<String,User> userMap = new HashMap<String,User>();
        for (int x = 0; x < 10; x++) {
            User u = new User();
            u.setUserName("刘宇轩"+x);
            u.setAge(18);
            u.setUserId(9527L+x);
            u.setSalary(99.9);
            u.setBirthday(new Date());
            userMap.put("mldn-" + x, u);
        }
        model.addAttribute("allUsers",userMap);
        return "user_map";
    }
    /*
    * list赋值
    * */
    @GetMapping(value = "/user/list")
    public String userList(Model model){
        List<User> userList = new ArrayList<User>();
        for (int x = 0; x < 10; x++) {
            User u = new User();
            u.setUserName("李鹏"+x);
            u.setAge(188);
            u.setUserId(9527L+x);
            u.setSalary(66.66);
            u.setBirthday(new Date());
            userList.add(u);
        }
        model.addAttribute("allUsers",userList);
        return "user_list";
    }


}
