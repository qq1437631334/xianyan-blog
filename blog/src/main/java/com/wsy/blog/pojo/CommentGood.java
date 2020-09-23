package com.wsy.blog.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wsy
 * @date 2020-08-18 9:29
 */
@Data
public class CommentGood implements Serializable {

    /**
     * 评论点赞id
     */
    @Id
    private String id;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 评论实体
     */
    private Comment comment;

    /**
     * 博客id
     */
    private String blogId;

    /**
     * 博客实体
     */
    private Blog blog;

    /**
     * 评论用户id
     */
    private Integer userId;

    /**
     * 评论用户
     */
    private User user;

    /**
     * 点赞时间
     */
    private Date createdTime;

}
