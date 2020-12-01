package com.wsy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsy.blog.annotation.Log;
import com.wsy.blog.constant.Constants;
import com.wsy.blog.dto.LoginDto;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.pojo.User;
import com.wsy.blog.service.UserService;
import com.wsy.blog.token.UsernamePasswordToken;
import com.wsy.blog.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wsy
 * @date 2020-08-10 20:21
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据id查询用户
     * @param id id
     * @return 结果集
     */
    @GetMapping("getById/{id}")
    public Result<User> getById(@PathVariable Integer id){
        return new Result<>(userService.getById(id));
    }

    /**
     * 保存方法
     * @param user 用户 实体
     * @return  结果集
     */
    @Log(title = "保存用户")
    @PostMapping("save")
    public Result<Object> save(@RequestBody User user) {
        userService.save(user);
        return new Result<Object>("添加成功！");
    }

    /**
     * 根据id删除用户
     * @param id id
     * @return  结果集
     */
    @Log(title = "删除用户")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return new Result("删除成功！");
    }

    /**
     * 根据id更新用户
     * @param user
     */
    @Log(title = "更新用户")
    @PutMapping("update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result("更新成功！");
    }

    /**
     * 根据id修改用户密码
     * @param id
     */
    @Log(title = "修改用户密码")
    @PutMapping("updatePassword/{id}&{password}")
    public Result update(@PathVariable Integer id, @PathVariable String password) {
        userService.updatePassword(id, password);
        return new Result("修改成功！");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("getPage")
    public Result<PageInfo<User>> getPage(@RequestBody Page page){
        PageUtils.checkParams(page, Constants.MUSIC_ORDER_BY_FIELDS);
        //设置当前页面和每页条数
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderByField() + " " + page.getOrderByMode());
        List<User> list = userService.getPage();
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setTotal(startPage.getTotal());
        pageInfo.setPages(startPage.getPages());
        return new Result<>("查询成功！",pageInfo);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Log(title = "注册用户")
    @PostMapping("register")
    public Result register(@RequestBody User user){
        if(userService.getByUsername(user.getUsername()) != null){
            throw new BlogException(ResultEnum.USERNAME_EXIST);
        }
        userService.save(user);
        return new Result("注册成功！");
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
        AuthenticationToken token = new UsernamePasswordToken(StateEnum.USER.getCode(),loginDto.getUsername(),loginDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        //做登录操作
        try{
            subject.login(token);
            //登录成功
            Serializable sessionId = subject.getSession().getId();
            User loginUser = (User)ShiroUtils.getLoginUser();
            Map<String, Object> resultMap = new HashMap<>(8);
            resultMap.put("token",sessionId);
            resultMap.put("userInfo",loginUser);
            return new Result("登陆成功",resultMap);
        } catch (Exception e) {
            //登录失败
            e.printStackTrace();
            return new Result<>(ResultEnum.TOKEN_ERROR);
        }
    }

    /**
     * 分页查询收藏
     * @param page
     * @return
     */
    @PostMapping("getCollectionPage")
    public Result getCollectionPage(@RequestBody Page page) {
        page = userService.getCollectionPage(page);
        return new Result(page);
    }

    /**
     * 获得评论数和收藏数
     * @return
     */
    @GetMapping("getCommentAndCollectionCount")
    public Result getCommentAndCollectionCount(){
        Map<String,Object> map = userService.getCommentAndCollectionCount();
        if(map == null) {
            return new Result(ResultEnum.NOT_LOGIN);
        }
        return new Result(map);
    }

    /**
     * 取消收藏
     * @param id
     * @return
     */
    @Log(title = "取消收藏")
    @DeleteMapping("deleteCollection/{id}")
    public Result deleteCollection(@PathVariable String id) {
        userService.deleteCollection(id);
        return new Result("取消成功！");
    }
}
