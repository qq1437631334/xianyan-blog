package com.wsy.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友情链接表
 *
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link implements Serializable {
    /**
     * 链接id
     */
    private Integer linkId;

    /**
     * 名称
     */
    private String linkName;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 创建时间
     */

    private Date createdTime;

    /**
     * 更新时间
     */

    private Date updateTime;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}