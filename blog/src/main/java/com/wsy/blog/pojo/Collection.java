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
 * 收藏时间
 * @author wsy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection implements Serializable {
    /**
     * 收藏id
     */
    @Id
    private String id;

    /**
     * 收藏人
     */
    private User User;

    /**
     * 收藏人id
     */
    private Integer userId;

    /**
     * 收藏博客
     */
    private Blog Blog;
    /**
     * 评论博客id
     */
    private String BlogId;

    /**
     * 收藏时间
     */
    private String createdTime;

    private static final long serialVersionUID = 1L;
}