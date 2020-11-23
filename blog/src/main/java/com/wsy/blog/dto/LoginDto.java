package com.wsy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wsy
 * @date 2020-11-23 21:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
