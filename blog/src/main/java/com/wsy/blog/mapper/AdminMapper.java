package com.wsy.blog.mapper;

import com.wsy.blog.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Mapper
public interface AdminMapper {
    /**
     * 根据用户名查询admin
     * @param username
     * @return
     */
    Admin getByUsername(String username);

    /**
     * 获得管理员
     * @return
     */
    Admin getAdmin();

    /**
     * 更新管理员信息
     * @param admin
     */
    void update(Admin admin);
}