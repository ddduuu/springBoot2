package com.hx.demo.student.controller;


import com.hx.demo.student.entity.TabStudent;
import com.hx.demo.student.service.ITabStudentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 表注释 前端控制器
 * </p>
 *
 * @author 杜志红
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/student/tab-student")
@AllArgsConstructor
public class TabStudentController {
    private static final Logger log = LoggerFactory.getLogger(TabStudentController.class);

    private ITabStudentService tabStudentService;

    @RequestMapping("/getAllStudent")
    public List<TabStudent> getAllStudent(){
        List<TabStudent> list = tabStudentService.list();
        log.info(list.toString());
        return list;
    }

}
