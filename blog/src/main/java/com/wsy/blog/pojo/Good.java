package com.wsy.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author wsy
 * @date 2020-08-17 12:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good implements Serializable {

    /**
     * 点赞id
     */
    @Id
    private String id;

    /**
     * 博客id
     */
    private String blogId;

    /**
     * 被点赞的博客
     */
    private Blog blog;

    /**
     * 点赞用户id
     */
    private Integer userId;

    /**
     * 点赞的用户
     */
    private User user;

    /**
     * 点赞时间
     */
    private String createdTime;
}
