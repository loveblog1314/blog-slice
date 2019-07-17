package com.itsure.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itsure.admin.dto.UserInfo;
import com.itsure.admin.entity.User;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface UserMapper extends BaseMapper<User> {

    UserInfo findUserInfo(String userName);

}
