package com.example.controller;

import com.example.component.SqlService;
import com.example.service.StatisticsService;
import com.hx.demo.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author dzh
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
@Api(tags = "查询统计")
@Slf4j
public class StatisticsController {

    private IUserService userService;
    private SqlService sqlService;
    private StatisticsService statisticsService;
    /***
     * 通过mybatis-plus运行原生sql语句
     * @return
     */
    @ApiOperation(value = "获取mybatis统计表",notes = "通过mysql-plus执行原生sql查询")
    @RequestMapping("/getMybatisStatisticsAll")
    public List<Map<String, Object>> getMybatisStatisticsAll(String startDate,String endDate){
        String sql = "select * from RE06";
        log.info(sql);
        List<Map<String, Object>> list = null;
        try {
            list = userService.getDataList(sql);
            Map dataMap = statisticsService.getDataMap(startDate, endDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    /***
     * 通过jpa运行原生sql语句
     * @return
     */
    @RequestMapping("/getJpaStatisticsAll")
    @ApiOperation(value = "获取jpa统计表",notes = "通过jpa执行原生sql查询")
    public List<Map<String,Object>> getJpaStatisticsAll(){
        String sql = "select * from RE06";
        List<Map<String, Object>> maps = sqlService.queryBySql(sql);
        return null;
    }

}
