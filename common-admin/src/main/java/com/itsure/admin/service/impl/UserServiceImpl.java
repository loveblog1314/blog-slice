package com.itsure.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itsure.admin.dto.UserInfo;
import com.itsure.admin.entity.User;
import com.itsure.admin.mapper.UserMapper;
import com.itsure.admin.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserInfo findUserInfo(String userName) {
        return this.baseMapper.findUserInfo(userName);
    }
}