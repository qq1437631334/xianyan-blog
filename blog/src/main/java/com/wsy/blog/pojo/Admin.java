package com.wsy.blog.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    /**
     * 管理员id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String header;

    /**
     * 签名
     */
    private String signature;

    /**
     * 介绍
     */
    private String comment;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private static final long serialVersionUID = 1L;
}