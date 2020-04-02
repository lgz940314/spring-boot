package com.krupp.mybatisplus.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krupp.mybatisplus.common.mapper.UserMapper;
import com.krupp.mybatisplus.common.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuguangzheng
 * @since 2019-11-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByAge(int i) {
        return userMapper.findByAge(i);
    }
}
