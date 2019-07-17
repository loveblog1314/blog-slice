package com.itsure.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itsure.admin.mapper.MenuMapper;
import com.itsure.admin.entity.Menu;
import com.itsure.admin.service.IMenuService;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


}