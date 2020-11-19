package com.wsy.blog.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 帖子类型表
 *
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {
    /**
     * 分类id
     */
    private Integer typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 帖子数
     */
    private Integer typeBlogCount;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}