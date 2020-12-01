package com.wsy.blog.config;

import com.wsy.blog.constant.Constants;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author wsy
 * @date 2020-10-02 17:38
 * 生成Token
 * 如果没有Token则生成一个返回到前台
 * 如果有Token就从请求头中取出来
 */
@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {
    /**
     * 获得session id
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //尝试从header中取出token
        String token = WebUtils.toHttp(request).getHeader(Constants.TOKEN);
        if(null != token) {
            return token;
        } else {
            return UUID.randomUUID().toString();
        }
    }
}
