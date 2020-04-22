package com.hx.demo.student.controller;


import com.hx.demo.student.entity.User;
import com.hx.demo.student.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杜志红
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/student/user")
@AllArgsConstructor
public class UserController {


    private IUserService iuserService;

    @RequestMapping("/getAllUser")
    public List<User> getAllUser(){
        List<User> list = iuserService.listasa();
        List<User> list1 = iuserService.list();
        return list;
    }



}
