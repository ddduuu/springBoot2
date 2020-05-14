package com.hx.demo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hx.demo.user.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 杜志红
 * @since 2020-04-21
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    List<User> selectUser();

}
