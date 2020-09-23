package com.wsy.blog.utils;

import com.wsy.blog.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果
 * @author wsy
 * @date 2020-08-09 21:25
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认为成功
     */
    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(String msg) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = msg;
    }

    public Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(String msg, T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }


    public Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }
}
