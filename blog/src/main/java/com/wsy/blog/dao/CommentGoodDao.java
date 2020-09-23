package com.wsy.blog.dao;

import com.wsy.blog.pojo.CommentGood;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-18 9:33
 */
public interface CommentGoodDao extends MongoRepository<CommentGood,String> {
    /**
     * 根据评论id博客id用户id查询是否点赞
     * @param commentId
     * @param blogId
     * @param userId
     * @return
     */
    int countByCommentIdAndBlogIdAndUserId(String commentId, String blogId, Integer userId);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    List<CommentGood> getByUserId(Integer userId);
}
