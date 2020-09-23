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
    private String baseUrl;
    private List<String> allowTypes;
}
