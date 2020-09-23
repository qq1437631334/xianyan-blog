package com.wsy.blog.exception;

import com.wsy.blog.enums.ResultEnum;

/**
 * 自定义异常
 * @author wsy
 * @date 2020-08-09 21:23
 */
public class BlogException extends RuntimeException {
    private Integer errorCode = ResultEnum.ERROR.getCode();

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(String msg) {
        super(msg);
    }

    public BlogException(Throwable throwable) {
        super(throwable);
    }

    public BlogException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public BlogException(ResultEnum paramsNull, String msg) {
        super(msg);
        this.errorCode = paramsNull.getCode();
    }

    public BlogException(Integer code, String msg) {
        super(msg);
        this.errorCode = code;
    }


    public Integer getErrorCode() {
        return errorCode;
    }
}
