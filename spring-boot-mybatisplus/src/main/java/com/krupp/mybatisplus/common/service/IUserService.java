package com.krupp.mybatisplus.common.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuguangzheng
 * @since 2019-11-21
 */
public interface IUserService extends IService<User> {
    User findByAge(int i);
}
