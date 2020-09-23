package com.wsy.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author wsy
* @date 2020-08-10 10:40
*/

/**
 * 关于闲言表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class About implements Serializable {
    /**
     * 帖子id
     */
    private String aboutId;

    /**
     * 标题
     */
    private String aboutTitle;

    /**
     * 帖子内容
     */
    private String aboutContent;

    /**
     * 阅读数
     */
    private Integer aboutRead;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GTM+8")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GTM+8")
    private Date updateTime;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;

    private static final long serialVersionUID = 1L;
}