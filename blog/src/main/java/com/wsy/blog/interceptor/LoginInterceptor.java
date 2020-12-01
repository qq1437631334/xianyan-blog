package com.wsy.blog.interceptor;

import com.wsy.blog.config.InterceptorConfig;
import com.wsy.blog.config.ShiroFilterConfig;
import com.wsy.blog.constant.Constants;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.utils.ShiroUtils;
import com.wsy.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: WSY
 * @date: 2020/8/10 11:34
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private InterceptorConfig interceptorConfig;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (containsWhiteList(request.getRequestURI())) {
            return true;
        }
        // 获取token
        String token = request.getHeader(Constants.TOKEN);
        if (StringUtils.isNotBlank(token)) {
            // token不为空，获取当前登录用户
            String loginInfo = ShiroUtils.getLoginInfo();
            if (loginInfo != null) {
                // 说明token有效
                return true;
            }
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 判断url是否在白名单内
     *
     * @param s
     * @return
     */
    private boolean containsWhiteList(String s) {
        List<String> anons = this.interceptorConfig.getWhiteList();
        for (String url : anons) {
            if (s.contains(url)) {
                return true;
            }
        }
        return false;
    }

}
