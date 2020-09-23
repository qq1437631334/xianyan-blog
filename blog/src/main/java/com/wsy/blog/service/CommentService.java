package com.wsy.blog.service;

import com.wsy.blog.pojo.Comment;
import com.wsy.blog.utils.Page;

import java.util.List;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
public interface CommentService{
    /**
     * 保存评论
     * @param comment
     */
    void save(Comment comment);

    /**
     * 根据博客id查询
     * @param id
     * @return
     */
    List<Comment> getByBlogId(String id);

    /**
     * 根据博客id删除
     * @param id
     */
    void deleteByBlogId(String id);

    /**
     * 保存评论点赞
     * @param blogId
     * @param commentId
     */
    void commentGood(String blogId, String commentId);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Comment> getPage(Page page);

    /**
     * 后台分页查询
     * @param page
     * @return
     */
    Page<Comment> getPageBack(Page page);

    /**
     * 根据id删除
     * @param id
     */
    void delete(String id);
}
