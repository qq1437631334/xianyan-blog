package com.wsy.blog.dao;

import com.wsy.blog.pojo.Comment;
import com.wsy.blog.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-16 20:02
 */
public interface CommentDao extends MongoRepository<Comment, String> {

    /**
     * 根据博客id获得评论
     * @param id
     * @return
     */
    List<Comment> getCommentsByCommentBlogIdOrderByCreatedTimeDesc(String id);

    /**
     * 根据评论id查询
     * @param id
     * @return
     */
    Comment getById(String id);

    /**
     * 根据登录用户id查询评论数
     * @param userId
     * @return
     */
    int countByCommentUserId(Integer userId);

    /**
     * 根据用户id查询评论
     * @param userId
     * @return
     */
    List<Comment> getByCommentUserId(Integer userId);

    /**
     * 根据评论id查询博客id
     * @param id
     * @return
     */
    String getCommentBlogIdById(String id);
}
