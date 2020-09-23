package com.wsy.blog.realm;

import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.pojo.Admin;
import com.wsy.blog.pojo.User;
import com.wsy.blog.service.AdminService;
import com.wsy.blog.service.UserService;
import com.wsy.blog.token.UsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wsy
 * @date 2020-08-10 10:56
 */
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    /**
     * 做授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (token.getState() == StateEnum.ADMIN.getCode()) {
            Admin admin = adminService.getByUsername(username);
            //判断是否查询到该用户名
            if (null == admin) {
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在！");
            }
            return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
        } else {
            User user = userService.getByUsername(username);
            //判断是否查询到该用户名
            if (null == user) {
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在！");
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }
    }
}
