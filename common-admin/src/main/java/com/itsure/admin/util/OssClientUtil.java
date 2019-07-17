package com.itsure.admin.util;

import com.aliyun.oss.*;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 阿里云 OSS客户端连接工具类
 *
 * @author itsure
 * @email 494471788@qq.com
 * @date 2019-07-08 13:01:19
 */
public class OssClientUtil {

    private static Logger log = Logger.getLogger(OssClientUtil.class);

    private static OSS ossClient = null;

    public static void init(String endPoint, String accessId, String secret){
       ossClient = new OSSClientBuilder().build(endPoint,accessId,secret);
    }

    public static Boolean uploadFile2Oss(InputStream inputStream, String originFileName, String bucketName, String dir, Integer expiredTime) {
        try {
            if(StringUtils.isNotEmpty(uploadFile2OSS(inputStream, originFileName, bucketName, dir, expiredTime))) {
                return true;
            }
            log.error("uploadFile2OSS error");
            return false;
        } catch (Exception e) {
            log.error("uploadFile2Oss fail", e);
            return false;
        }
    }
    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @param
     * @return 出错返回"" ,唯一MD5数字签名
     */
    private static String uploadFile2OSS(InputStream instream, String fileName, String bucketName, String dir, Integer expiredMinute) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setHeader("Access-Control-Allow-Origin", "*");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            if(expiredMinute != null) {
                objectMetadata.setExpirationTime(new Date(System.currentTimeMillis() + expiredMinute * 60 * 1000));
            }
            //上传文件
            StringBuilder filePath = new StringBuilder().append(dir).append("/").append(fileName);
            if (ossClient == null) {
                log.error("ossClient not init");
                return null;
            }
            PutObjectResult putResult = ossClient.putObject(bucketName, filePath.toString(), instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            log.error("ossClient.putObject error....", e);
            return null;
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ret);
        return ret;
    }

    public static InputStream downloadFile(String bucketName, String objectName) {
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        return ossObject.getObjectContent();
    }

    public static ObjectMetadata downloadFile(String bucketName, String objectName, String filePath) {
        return ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(filePath));
    }

    public static void deleteFile(String bucketName, String objectName) {
        try {
            ossClient.deleteObject(bucketName, objectName);
        }catch(OSSException | ClientException e) {
            log.error("ossClient.deleteObject error... bucketName = " + bucketName +", objectName = " + objectName, e);
        }
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if(FilenameExtension.equalsIgnoreCase(".mp3")) {
            return "audio/mp3";
        }
        if(FilenameExtension.equalsIgnoreCase(".mp4")) {
            return "video/mpeg4";
        }
        return "image/jpeg";
    }
}
