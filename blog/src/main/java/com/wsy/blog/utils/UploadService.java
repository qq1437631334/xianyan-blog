package com.wsy.blog.utils;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.wsy.blog.config.UploadConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 上传服务
 * @author wsy
 */
@Component
@EnableConfigurationProperties(UploadConfig.class)
public class UploadService {

    private final UploadConfig uploadConfig;

    public UploadService(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }


    /**
     * 构造一个带指定 Region 对象的配置类
     */
    Configuration cfg = new Configuration(Region.huanan());
    /**
     * ...其他参数参考类注释
     */
    UploadManager uploadManager = new UploadManager(cfg);



    public String upload( MultipartFile mf) {
        if (!mf.isEmpty()) {
            try {
                //将文件转换为字节数据
                byte[] fileBytes = mf.getBytes();
                //生成上传凭证
                Auth auth = Auth.create(uploadConfig.getAccessKey(), uploadConfig.getSecretKey());
                String upToken = auth.uploadToken(uploadConfig.getBucket());
                //key 不指定的话文件存储名就为Hash值
                String key = uploadConfig.getPicturePrefix() + mf.hashCode();
                Response response = uploadManager.put(fileBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                String fileUrl = uploadConfig.getBaseUrl() + putRet.key;
                Map<String,Object> res = new HashMap<>(8);
                return fileUrl;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
