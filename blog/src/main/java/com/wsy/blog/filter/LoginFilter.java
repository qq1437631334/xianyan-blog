package com.wsy.blog.filter;

import com.alibaba.fastjson.JSONObject;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.utils.Result;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author wsy
 * @date 2020-08-18 15:19
 */
public class LoginFilter extends UserFilter {
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(new Result<>(ResultEnum.NOT_LOGIN)));
    }
}
