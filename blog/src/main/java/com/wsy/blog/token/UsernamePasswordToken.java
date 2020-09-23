package com.wsy.blog.token;

import lombok.Data;
import lombok.Getter;

/**
 * @author wsy
 * @date 2020-08-16 10:45
 */
@Data
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {
    private int state;

    public UsernamePasswordToken(int state,String username, String password) {
        super(username, password);
        this.state = state;
    }
}
