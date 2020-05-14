package com.example.controller;

import com.example.component.SqlService;
import com.hx.demo.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author dzh
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private IUserService userService;
    private SqlService sqlService;

    /***
     * 通过mybatis-plus运行原生sql语句
     * @return
     */
    @RequestMapping("/getMybatisStatisticsAll")
    public List<Map<String, Object>> getMybatisStatisticsAll(){
        String sql = "select * from RE06";
        List<Map<String, Object>> list = userService.getStatisticsAll(sql);
        return list;
    }
    /***
     * 通过jpa运行原生sql语句
     * @return
     */
    @RequestMapping("/getJpaStatisticsAll")
    public List<Map<String,Object>> getJpaStatisticsAll(){
        String sql = "select * from RE06";
        List<Map<String, Object>> maps = sqlService.queryBySql(sql);
        return maps;
    }

}
