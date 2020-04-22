package com.hx.demo.student.service.impl;

import com.hx.demo.student.entity.User;
import com.hx.demo.student.mapper.UserMapper;
import com.hx.demo.student.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杜志红
 * @since 2020-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> listasa() {
        List<User> u = baseMapper.selectList(null);
        return u;
    }


}
