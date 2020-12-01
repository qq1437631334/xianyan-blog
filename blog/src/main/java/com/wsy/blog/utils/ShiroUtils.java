package com.wsy.blog.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Shiro相关工具类
 *
 * @Author: 杨德石
 * @Date: 2019/5/9 0009 下午 8:43
 * @Version 1.0
 */
@Component
public class ShiroUtils {

    private ShiroUtils() {
    }

    /**
     * 获取登录中的用户
     *
     * @return
     */
    public static Object getLoginUser() {
        //判断是否有session如果没有也不创建session
        Session session = SecurityUtils.getSubject().getSession(false);
        if (null != session) {
            SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (principalCollection == null) {
                return null;
            }
            return principalCollection.getPrimaryPrincipal();
        }
        return null;
    }

    /**
     * 获取登录中的用户的名字和用户名
     *
     * @return 登录中的用户的名字和用户名
     */
    public static String getSimpleName() {
        Object loginUser = getLoginUser();
        if (null != loginUser) {
            JSONObject user = JSON.parseObject(JSON.toJSON(loginUser).toString());
            //获取用户名和姓名
            String username = user.get("username").toString();
            String name = user.get("name").toString();
            return name + ": " + username;
        }else {
            return null;
        }
    }

    /**
     * 获得登陆者的信息
     * @return  登陆者的信息
     */
    public static String getLoginInfo() {
        Object loginUser = getLoginUser();
        if(null != loginUser) {
            return JSON.toJSONString(loginUser);
        }
        return null;
    }
}
