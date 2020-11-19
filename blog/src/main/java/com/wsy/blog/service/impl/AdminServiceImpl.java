package com.wsy.blog.service.impl;

import com.wsy.blog.pojo.Admin;
import com.wsy.blog.utils.Md5Utils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsy.blog.mapper.AdminMapper;
import com.wsy.blog.service.AdminService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin getByUsername(String username) {
        return adminMapper.getByUsername(username);
    }

    @Override
    public Admin getAdmin() {
        return adminMapper.getAdmin();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInfo(Admin admin) {
        adminMapper.update(admin);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Admin admin) {
        //先查询之前的老管理员信息
        Admin oldAdmin = adminMapper.getAdmin();
        oldAdmin.setPassword(Md5Utils.toMD5(admin.getPassword()));
        adminMapper.update(admin);
    }
}

