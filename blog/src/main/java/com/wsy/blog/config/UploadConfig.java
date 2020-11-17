package com.wsy.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-13 14:28
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {
    /**
     * 设置您七牛云配置的域名
     */

    private String baseUrl;

    /**
     * 设置七牛云的秘钥和存储桶
     */
    private String accessKey;
    private String secretKey;
    private String bucket;
    /**
     * 上传文件的前缀
     */
    private String picturePrefix;
}
