package com.wsy.blog.vo;

import com.wsy.blog.constant.Const;
import com.wsy.blog.pojo.Blog;
import lombok.Data;

/**
 * @author wsy
 * @date 2020-08-11 15:26
 */
@Data
public class BlogVo extends Blog {
    /**
     * 类型名字
     */
    private String typeName;

    /**
     * 发布月份
     */
    private String blogMonth;

}
