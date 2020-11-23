package com.wsy.blog.controller;

import com.wsy.blog.annotation.Log;
import com.wsy.blog.dto.LoginDto;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.pojo.Admin;
import com.wsy.blog.service.AdminService;
import com.wsy.blog.token.UsernamePasswordToken;
import com.wsy.blog.utils.Md5Utils;
import com.wsy.blog.utils.Result;
import com.wsy.blog.utils.ShiroUtils;
import com.wsy.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wsy
 * @date 2020-08-10 10:58
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 登录功能
     * @param loginDto 登录账号实体
     * @return 结果集
     */
    @Log(title = "登录")
    @PostMapping("login")
    public Result<Object> login(@RequestBody LoginDto loginDto){
        //判断参数是否为空
        if(null == loginDto || StringUtils.isNullOrEmpty(loginDto.getUsername()) || StringUtils.isNullOrEmpty(loginDto.getPassword())){
            return new Result<Object>(ResultEnum.PARAMS_NULL,"用户名或密码不能为空！");
        }
        AuthenticationToken token = new UsernamePasswordToken(StateEnum.ADMIN.getCode(),loginDto.getUsername(), Md5Utils.toMD5(loginDto.getPassword()));
        Subject subject = SecurityUtils.getSubject();
        //做登录操作
        try{
            subject.login(token);
        } catch (Exception e) {
            //登录失败
            e.printStackTrace();
            return new Result<>(ResultEnum.TOKEN_ERROR);
        }
        //登录成功
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object>resultMap = new HashMap<>(8);
        resultMap.put("token",sessionId);
        return new Result<>(resultMap);
    }

    /**
     * 获得当前登录信息
     * @return 结果集
     */
    @Log(title = "获取当前登录信息")
    @GetMapping("info")
    public Result<Admin> info(){
        Admin loginUser = (Admin) ShiroUtils.getLoginUser();
        if(loginUser!=null) {
            loginUser.setPassword(null);
            return new Result<>(loginUser);
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 获得管理员
     * @return 结果集
     */
    @GetMapping("getAdmin")
    public Result<Admin> getAdmin(){
       Admin admin = adminService.getAdmin();
       return new Result<>(admin);
    }

    /**
     * 更新管理员信息
     * @param admin 管理员实体
     * @return 结果集
     */
    @Log(title = "更新管理员信息")
    @PutMapping("updateInfo")
    public Result updateInfo(@RequestBody Admin admin){
        adminService.updateInfo(admin);
        return new Result("修改信息成功！");
    }

    /**
     * 更新管理员密码
     * @param admin 管理员实体
     * @return  结果集
     */
    @Log(title = "更新管理员密码")
    @PutMapping("updatePassword")
    public Result updatePassword(@RequestBody Admin admin){
        adminService.updatePassword(admin);
        return new Result("修改密码成功！");
    }
}
