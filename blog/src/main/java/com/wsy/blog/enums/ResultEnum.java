package com.wsy.blog.enums;

import lombok.Getter;

/**
 * 返回结果枚举
 * @author wsy
 * @date 2020-08-09 21:08
 */

@Getter
public enum ResultEnum {
    /**
     * 枚举
     */
    SUCCESS(20000, "操作成功！"),
    ERROR(40000, "操作失败！"),
    DATA_NOT_FOUND(40001, "查询失败！"),
    PARAMS_NULL(40002, "参数不能为空！"),
    NOT_LOGIN(40003, "当前账号未登录！"),
    PARAMS_ERROR(40005, "参数不合法！"),
    TOKEN_ERROR(400006,"用户名或密码错误！"),
    TYPE_EXIST(400007,"类型已存在！"),
    RE_ENABLE(400008,"请不要重复启用！"),
    RE_DISABLE(400009,"请不要重复弃用！"),
    EXIST_ENABLE(400010,"当前已有启用关于我的！"),
    USERNAME_EXIST(400011,"该用户名已存在！")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
