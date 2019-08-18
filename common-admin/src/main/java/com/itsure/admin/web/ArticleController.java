package com.itsure.admin.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itsure.admin.dto.ResultInfo;
import com.itsure.admin.dto.UserInfo;
import com.itsure.admin.entity.Article;
import com.itsure.admin.service.IArticleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 用户文章 前端控制器
 *
 * @author itsure
 * @email 494471788@qq.com
 * @date 2019-07-08 13:01:19
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends  BaseController{
    @Resource
    private IArticleService iArticleService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("article:view")
    public @ResponseBody
    ResultInfo<List<Article>> listData(Article article, Integer page, Integer limit){
        EntityWrapper<Article> wrapper = new EntityWrapper<>(article);
        Page<Article> pageObj = iArticleService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("article:add")
    public @ResponseBody
    ResultInfo<Boolean> add(Article article){
        // 判断是否已登录，如果已登录直接跳转到首页
        UserInfo userInfo = this.getUserInfo();
        if (userInfo != null){
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
            article.setAuthor(userInfo.getUserName());
            article.setWordCount(count(article.getContent()));
            boolean b = iArticleService.insert(article);
            return new ResultInfo<>(b);
        } else {
            return new ResultInfo<>(false);
        }

    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("article:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iArticleService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("article:edit")
    public @ResponseBody
    ResultInfo<Boolean> edit(Article article){
        article.setUpdateTime(new Date());
        article.setWordCount(count(article.getContent()));
        boolean b = iArticleService.updateById(article);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/lists")
    @RequiresPermissions("article:view")
    public @ResponseBody
    ResultInfo<List<Article>> list(Article article,Integer menuId, Integer page, Integer limit){
        article.setMenuId(menuId);
        EntityWrapper<Article> wrapper = new EntityWrapper<>(article);
        Page<Article> pageObj = iArticleService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    public static int count(String text) {
        String Reg="^[\u4e00-\u9fa5]{1}$";//正则
        int result=0;
        for(int i=0;i<text.length();i++){
            String b=Character.toString(text.charAt(i));
            if(b.matches(Reg))result++;
        }
        return result;
    }
}
