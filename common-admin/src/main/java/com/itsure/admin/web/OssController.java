package com.itsure.admin.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itsure.admin.dto.ResultInfo;
import com.itsure.admin.entity.Oss;
import com.itsure.admin.service.IOssService;
import com.itsure.admin.util.OssClientUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static com.sun.tools.doclint.Entity.Mu;

/**
 * 对象存储 前端控制器
 *
 * @author itsure
 * @email 494471788@qq.com
 * @date 2019-07-13 18:56:52
 */
@Controller
@RequestMapping("/oss")
public class OssController {
    @Resource
    private IOssService iOssService;
    public static String DIR_IMG = "img";

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("oss:view")
    public @ResponseBody
    ResultInfo<List<Oss>> listData(Oss oss, Integer page, Integer limit){
        EntityWrapper<Oss> wrapper = new EntityWrapper<>(oss);
        Page<Oss> pageObj = iOssService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("oss:add")
    public @ResponseBody
    ResultInfo<Boolean> add(Oss oss){
        oss.setStatus(0);
        EntityWrapper<Oss> wrapper = new EntityWrapper<>(oss);
        List<Oss> ossList = iOssService.selectList(wrapper);
        for (Oss oss1 : ossList) {
            oss1.setStatus(-1);
        }
        if (ossList.size() > 0) {
            iOssService.updateBatchById(ossList);
        }
        boolean b = iOssService.insert(oss);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("oss:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr){
        if (idArr == null) {
            return new ResultInfo<>(Boolean.FALSE);
        }
        List<Oss> ossList = new ArrayList<>();
        for (Integer id : idArr) {
            Oss oss = new Oss();
            oss.setId(id);
            oss.setStatus(-1);
            ossList.add(oss);
        }
        boolean b = iOssService.updateBatchById(ossList);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("oss:edit")
    public @ResponseBody
    ResultInfo<Boolean> edit(Oss oss){
        boolean b = iOssService.updateById(oss);
        return new ResultInfo<>(b);
    }

    @RequestMapping(value = "/upload",  method = RequestMethod.POST )
    @ResponseBody
    public Map upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Oss oss = new Oss();
        oss.setStatus(0);
        EntityWrapper<Oss> wrapper = new EntityWrapper<>(oss);
        Oss ossRet = iOssService.selectOne(wrapper);
        String url = "";
        OssClientUtil.init(ossRet.getEndPoint(), ossRet.getAccessId(), ossRet.getSecret());
        for (Map.Entry<String,MultipartFile>  file : fileMap.entrySet()) {
            try {
                String extName = file.getValue().getOriginalFilename().substring(file.getValue().getOriginalFilename().lastIndexOf("."));
                String fileName = UUID.randomUUID() + extName;
                OssClientUtil.uploadFile2Oss(file.getValue().getInputStream(), fileName, ossRet.getOssBucketName(), DIR_IMG, null);
                url = "https://" + ossRet.getOssPreffix() + "/" + DIR_IMG + "/" + fileName;

                Map<String,Object> map = new HashMap<String,Object>();
                map.put("original",file.getValue().getOriginalFilename());
                map.put("name",file.getValue().getOriginalFilename());
                map.put("url",url);
                map.put("size",file.getValue().getSize());
                map.put("type",extName);
                map.put("state","SUCCESS");
                map.put("code", 200);
                return map;
            } catch (IOException e) {
                Map<String,Object> result = new HashMap<>();
                result.put("error", 1);
                result.put("message", "上传图片失败");
                return result;
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("url",url);
        map.put("state","SUCCESS");
        map.put("code", 200);
        return  map;
    }

}
