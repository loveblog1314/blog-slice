package com.itsure.admin.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itsure.admin.dto.ResultInfo;
import com.itsure.admin.entity.Menu;
import com.itsure.admin.entity.Permission;
import com.itsure.admin.service.IMenuService;
import com.itsure.admin.service.IPermissionService;
import com.itsure.admin.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 前台菜单 前端控制器
 *
 * @author itsure
 * @email 494471788@qq.com
 * @date 2019-07-07 12:32:42
 */
@Controller
@RequestMapping("/menu")
public class FrontMenuController {
    @Resource
    private IMenuService iMenuService;
    @Resource
    private IPermissionService iPermissionService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("menu:view")
    public @ResponseBody
    ResultInfo<List<Menu>> listData(Menu menu, Integer page, Integer limit){
        EntityWrapper<Menu> wrapper = new EntityWrapper<>(menu);
        Page<Menu> pageObj = iMenuService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("menu:add")
    public @ResponseBody
    ResultInfo<Boolean> add(Menu menu){
        if (null != menu){
            menu.setCreateTime(new Date());
            menu.setUpdateTime(new Date());
        }
        boolean b = iMenuService.insert(menu);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("menu:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iMenuService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("menu:edit")
    public @ResponseBody
    ResultInfo<Boolean> edit(Menu menu){
        boolean b = iMenuService.updateById(menu);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/parentSelectData")
    public @ResponseBody
    ResultInfo<List<Menu>> parentSelectData(Integer id) {
        List<Menu> list = new ArrayList<>();
        Permission condition = new Permission();
        condition.setId(id);
        EntityWrapper<Permission> wrapper = new EntityWrapper<>(condition);
        if (null == id) {
            Permission condition1 = new Permission();
            condition1.setParentId(34);
            wrapper = new EntityWrapper<>(condition1);
        }
        List<Permission> definedMenu = iPermissionService.selectList(wrapper);
        for (Permission menu : definedMenu) {
            Menu m = new Menu();
            m.setId(menu.getId());
            m.setName(menu.getPermissionName());
            list.add(m);
        }
        return new ResultInfo<>(list);
    }

    @RequestMapping("/menuSelectData")
    public @ResponseBody
    ResultInfo<List<Menu>> menuSelectData() {
        List<Menu> list = new ArrayList<>();
        EntityWrapper<Menu> wrapper = new EntityWrapper<>();
        List definedMenu =  iMenuService.selectList(wrapper);
        list.addAll(definedMenu);
        return new ResultInfo<>(list);
    }
}
