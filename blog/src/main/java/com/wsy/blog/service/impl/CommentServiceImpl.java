package com.wsy.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wsy.blog.dao.CommentDao;
import com.wsy.blog.dao.CommentGoodDao;
import com.wsy.blog.mapper.BlogMapper;
import com.wsy.blog.pojo.Blog;
import com.wsy.blog.pojo.Comment;
import com.wsy.blog.pojo.CommentGood;
import com.wsy.blog.pojo.User;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.wsy.blog.service.CommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.unit.DataUnit;

import java.util.Date;
import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class CommentServiceImpl implements CommentService {


    private final CommentDao commentDao;

    private final CommentGoodDao commentGoodDao;

    private final BlogMapper blogMapper;

    public CommentServiceImpl(CommentDao commentDao, CommentGoodDao commentGoodDao, BlogMapper blogMapper) {
        this.commentDao = commentDao;
        this.commentGoodDao = commentGoodDao;
        this.blogMapper = blogMapper;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Comment comment) {
        blogMapper.comment(comment.getCommentBlogId());
        comment.setCommentBlog(blogMapper.getById(comment.getCommentBlogId()));
        User user = (User) ShiroUtils.getLoginUser();
        user.setPassword("");
        comment.setCommentUser(user);
        comment.setCommentUserId(user.getUserId());
        comment.setCommentGood(0);
        comment.setCreatedTime(DateUtil.date());
        commentDao.insert(comment);
    }

    @Override
    public List<Comment> getByBlogId(String id) {
        List<Comment> list = commentDao.getCommentsByCommentBlogIdOrderByCreatedTimeDesc(id);
        User loginUser = (User) ShiroUtils.getLoginUser();
        //如果用户已登录的话判断当前用户当前博客当前评论是否点赞
        if (loginUser != null) {
            for (Comment comment : list) {
                if (commentGoodDao.countByCommentIdAndBlogIdAndUserId(comment.getId(), id, loginUser.getUserId()) > 0) {
                    comment.setGood(true);
                }
            }
        }
        return list;
    }

    @Override
    public void deleteByBlogId(String id) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void commentGood(String blogId, String commentId) {
        CommentGood commentGood = new CommentGood();
        //设置评论点赞的博客
        commentGood.setBlogId(blogId);
        commentGood.setBlog(blogMapper.getById(commentGood.getBlogId()));
        User loginUser = (User) ShiroUtils.getLoginUser();
        //设置点赞的用户是谁
        commentGood.setUserId(loginUser.getUserId());
        commentGood.setUser(loginUser);
        commentGood.setCommentId(commentId);
        commentGood.setCreatedTime(DateUtil.date());
        //评论点赞数+1
        Comment comment = commentDao.getById(commentGood.getCommentId());
        comment.setCommentGood(comment.getCommentGood() + 1);
        commentDao.save(comment);
        commentGood.setComment(comment);
        commentGoodDao.save(commentGood);
    }

    @Override
    public Page<Comment> getPage(Page page) {
        Comment comment = new Comment();
        User loginUser = (User) ShiroUtils.getLoginUser();
        comment.setCommentUserId(loginUser.getUserId());
        Example<Comment> example = Example.of(comment);
        Pageable pageable = PageRequest.of(page.getPageNum() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> all = commentDao.findAll(example, pageable);
        page.setTotalCount((int) all.getTotalElements());
        page.setTotalPage(all.getTotalPages());
        page.setData(all.getContent());
        return page;
    }

    @Override
    public Page<Comment> getPageBack(Page page) {
        Pageable pageable = PageRequest.of(page.getPageNum() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> all = commentDao.findAll(pageable);
        page.setData(all.getContent());
        page.setTotalPage(all.getTotalPages());
        page.setTotalCount((int) all.getTotalElements());
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Comment comment = new Comment();
        comment.setId(id);
        Comment commentById = commentDao.getById(id);
        blogMapper.reduceComment(commentById.getCommentBlogId());
        commentDao.delete(comment);
    }
}

