package com.wsy.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
* @author wsy
* @date 2020-08-10 10:40
*/

/**
 * 评论表
 * @author wsy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @Id
    private String id;

    /**
     * 评论内容
     */
    private String commentContent;


    /**
     * 评价人
     */
    private User commentUser;

    /**
     * 评价人id
     */
    private Integer commentUserId;

    /**
     * 评论帖子
     */
    private Blog commentBlog;
    /**
     * 评论帖子id
     */
    private String commentBlogId;

    /**
     * 点赞数
     */
    private Integer commentGood;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    /**
     * 是否已经点赞
     */
    private boolean isGood = false;

    private static final long serialVersionUID = 1L;
}