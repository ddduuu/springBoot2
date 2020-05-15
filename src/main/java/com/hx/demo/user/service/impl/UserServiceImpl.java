package com.hx.demo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.demo.user.entity.User;
import com.hx.demo.user.mapper.UserMapper;
import com.hx.demo.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杜志红
 * @since 2020-04-21
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMappera;

    @Override
    public List<User> listasa() {
        List<User> list = userMappera.selectUser();
        List<User> u = baseMapper.selectList(null);
        this.sqlSessionBatch().getConnection();
        return u;
    }
    /***
     * 通过jpa运行原生sql语句
     * @param sql
     * @return
     */
    @Override
    public List<Map<String, Object>> getStatisticsAll(String sql) {
        Connection conn = this.sqlSessionBatch().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 得到结果集ResultSet的结构信息，比如字段数、字段名等
            ResultSetMetaData rsmd = rs.getMetaData();
            // 得到数据集的列数
            int columncount = rsmd.getColumnCount();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                for (int i = 0; i < columncount; i++) {
                    String key = rsmd.getColumnLabel(i+1);
                    //String key = rsmd.getColumnName(i + 1)
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null){
                    conn.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (rs != null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
