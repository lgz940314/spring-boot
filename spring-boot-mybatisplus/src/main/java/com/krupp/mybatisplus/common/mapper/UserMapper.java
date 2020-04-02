package com.krupp.mybatisplus.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuguangzheng
 * @since 2019-11-21
 */
public interface UserMapper extends BaseMapper<User> {

    User findByAge(int i);
}
