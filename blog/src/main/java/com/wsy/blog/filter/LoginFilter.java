package com.wsy.blog.filter;

import com.alibaba.fastjson.JSON;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.utils.Result;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wsy
 * @date 2020-08-18 15:19
 */
public class LoginFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(JSON.toJSONString(new Result<>(ResultEnum.NOT_LOGIN)));
        return false;
    }
}
