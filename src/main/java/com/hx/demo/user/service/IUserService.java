package com.hx.demo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hx.demo.user.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杜志红
 * @since 2020-04-21
 */
public interface IUserService extends IService<User> {

    List<User> listasa();

    List<Map<String, Object>> getDataList(String sql) throws SQLException;

    List<Map<String, Object>> getDataList(String tabName, List columnList, Map conditionMap, Map inConditionMap, Map notInConditionMap, Map likeConditionMap, Map notLikeConditionMap, List groupList, List orderList, String sqlScript)throws SQLException;


}
