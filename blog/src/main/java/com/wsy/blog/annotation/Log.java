package com.wsy.blog.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * @author wsy
 * @date 2020-10-16 14:17
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";
}
