package com.hx.demo.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hx.demo.student.entity.User;

import java.util.List;

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

}
