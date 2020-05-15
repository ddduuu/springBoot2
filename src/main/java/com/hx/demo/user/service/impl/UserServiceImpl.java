package com.hx.demo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.demo.user.entity.User;
import com.hx.demo.user.mapper.UserMapper;
import com.hx.demo.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

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
    public List<Map<String, Object>> getDataList(String sql) throws SQLException {
        Connection conn = this.sqlSessionBatch().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
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
            colseResources(conn,ps,rs);
            return list;
    }
    /**
     * 获取数据库表数据列表(通用)
     * @param tableName 数据库表名称
     * @param columnlist 查询字段名称
     * @param conditionMap 条件集合
     * @param inConditionMap in 条件集合
     * @param notInConditionMap not in 条件集合
     * @param likeConditionMap like条件集合
     * @param notLikeConditionMap not like条件集合
     * @param orderList 排序字段集合
     * @param sqlScript 附加SQL条件脚本
     * @return List 数据库表数据列表
     * @throws SQLException
     */
    public List getDataList(String tableName, List columnlist, Map conditionMap, Map inConditionMap, Map notInConditionMap, Map likeConditionMap, Map notLikeConditionMap, List groupList, List orderList, String sqlScript) throws SQLException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("select ");
        if(columnlist != null && columnlist.size() > 0){
            for(int i = 0;i < columnlist.size();i ++){
                if(i == 0){
                    buffer.append(columnlist.get(i));
                }else{
                    buffer.append(",").append(columnlist.get(i));
                }
            }
        }else{
            buffer.append(" *");
        }
        buffer.append(" from ");
        buffer.append(tableName).append(" where 1 = 1 ");
        if(conditionMap != null){
            for(Iterator it = conditionMap.keySet().iterator(); it.hasNext();){
                String key = (String)it.next();
                buffer.append(" and ").append(key).append(" = '").append(conditionMap.get(key)).append("' ");
            }
        }
        if(inConditionMap != null){
            for(Iterator it = inConditionMap.keySet().iterator();it.hasNext();){
                String key = (String)it.next();
                buffer.append(" and ").append(key).append(" in (").append(inConditionMap.get(key)).append(") ");
            }
        }
        if(notInConditionMap != null){
            for(Iterator it = notInConditionMap.keySet().iterator();it.hasNext();){
                String key = (String)it.next();
                buffer.append(" and ").append(key).append(" not in (").append(notInConditionMap.get(key)).append(") ");
            }
        }
        if(likeConditionMap != null){
            for(Iterator it = likeConditionMap.keySet().iterator();it.hasNext();){
                String key = (String)it.next();
                buffer.append(" and ").append(key).append(" like '%").append(likeConditionMap.get(key)).append("%' ");
            }
        }
        if(notLikeConditionMap != null){
            for(Iterator it = notLikeConditionMap.keySet().iterator();it.hasNext();){
                String key = (String)it.next();
                buffer.append(" and ").append(key).append(" not like '%").append(notLikeConditionMap.get(key)).append("%' ");
            }
        }
        if(sqlScript != null && !"".equals(sqlScript)){
            buffer.append(" and ").append(sqlScript);
        }
        if(groupList != null && !groupList.isEmpty()){
            boolean bo = true;
            for(int i = 0;i < groupList.size();i ++){
                String key = (String)groupList.get(i);
                if(bo){
                    buffer.append(" group by ");
                    buffer.append(key).append(" ");
                    bo = false;
                }else{
                    buffer.append(",").append(key).append(" ");
                }
            }
        }
        if(orderList != null && !orderList.isEmpty()){
            boolean bo = true;
            for(int i = 0;i < orderList.size();i ++){
                String key = (String)orderList.get(i);
                if(bo){
                    buffer.append(" order by ");
                    buffer.append(key).append(" ");
                    bo = false;
                }else{
                    buffer.append(",").append(key).append(" ");
                }
            }
        }
        String sql = buffer.toString();
        Connection conn = this.sqlSessionBatch().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        // 得到结果集ResultSet的结构信息，比如字段数、字段名等
        ResultSetMetaData rsmd = rs.getMetaData();
        // 得到数据集的列数
        int columncount = rsmd.getColumnCount();
        List<Map<String,Object>> list = new ArrayList();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < columncount; i++) {
                String key = rsmd.getColumnLabel(i+1);
                String value = rs.getString(key);
                map.put(key, value);
            }
            list.add(map);
        }
        colseResources(conn,ps,rs);
        return list;
    }

    public void colseResources(Connection conn,PreparedStatement ps,ResultSet rs) throws SQLException {
        if (rs != null){
            rs.close();
        }
        if (ps != null){
            ps.close();
        }
        if (conn != null){
            conn.close();
        }
    }

}
