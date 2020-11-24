package com.wsy.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author wsy
 * @date 2020-11-24 8:52
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "shiro.filter")
public class ShiroFilterConfig {
    /**
     * 放行的路径
     */
    List<String> anons;

    /**
     * 拦截的路径
     */
    List<String> authc;
}
