package com.itsure.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.itsure.admin.dto.UserInfo;
import com.itsure.admin.entity.User;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IUserService extends IService<User> {

    UserInfo findUserInfo(String userName);

}
