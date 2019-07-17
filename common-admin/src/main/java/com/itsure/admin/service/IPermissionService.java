package com.itsure.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.itsure.admin.dto.PermissionInfo;
import com.itsure.admin.dto.MenuInfo;
import com.itsure.admin.entity.Permission;

import java.util.List;

/**
 * <p>
 * 系统权限表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IPermissionService extends IService<Permission> {

     List<Permission> getAllPermissions();

     Boolean savePermission(Permission permission);

     Boolean delBatchPermission(List<Integer> ids);

     List<PermissionInfo> allPermissionInfo();

     List<MenuInfo> getMenuPermissions(String code);

     List<Permission> getTopDirectoryPermissions();

}