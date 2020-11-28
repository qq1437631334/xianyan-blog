package com.wsy.blog.utils;

import com.wsy.blog.pojo.User;

/**
 * @author wsy
 * @date 2020-11-28 23:49
 */
public class CheckLoginUserUtils {
    /**
     * 判断当前登录的用户是管理员还是普通用户
     * @param loginUser 当前登录的用户
     * @return  boolean true 代表是普通用户
     */
    public static boolean checkLoginUser(Object loginUser) {
        return loginUser instanceof User;
    }
}
