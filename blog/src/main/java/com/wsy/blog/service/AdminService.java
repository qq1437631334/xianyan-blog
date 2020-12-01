package com.wsy.blog.service;

import com.wsy.blog.pojo.Admin;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
public interface AdminService {
    /**
     * 根据用户名得到admin
     *
     * @param username
     * @return
     */
    Admin getByUsername(String username);

    /**
     * 获得管理员
     *
     * @return
     */
    Admin getAdmin();

    /**
     * 更新管理员信息
     *
     * @param admin
     */
    void updateInfo(Admin admin);

    /**
     * 更新管理员密码
     *
     * @param admin
     */
    void updatePassword(Admin admin);
}

