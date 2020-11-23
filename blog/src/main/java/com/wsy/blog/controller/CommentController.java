package com.wsy.blog.controller;

import com.wsy.blog.annotation.Log;
import com.wsy.blog.pojo.Comment;
import com.wsy.blog.service.CommentService;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-16 19:45
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 保存
     * @param comment
     * @return
     */
    @Log(title = "保存评论")
    @PostMapping("save")
    public Result save(@RequestBody Comment comment) {
        commentService.save(comment);
        return new Result("评论成功！");
    }

    /**
     * 根据博客id查询评论
     * @param blogId
     * @return
     */
    @GetMapping("getByBlogId/{blogId}")
    public Result<List<Comment>> getByBlogId( @PathVariable String blogId){
        return new Result<>(commentService.getByBlogId(blogId));
    }

    /**
     * 根据博客id 和评论id 点赞
     * @param blogId
     * @param commentId
     * @return
     */
    @Log(title = "评论点赞")
    @PostMapping("commentGood/{blogId}&{commentId}")
    public Result commentGood(@PathVariable String blogId, @PathVariable String commentId) {
        commentService.commentGood(blogId,commentId);
        return new Result("点赞成功！");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("getPage")
    public Result getPage(@RequestBody Page page) {
        page = commentService.getPage(page);
        return new Result(page);
    }

    /**
     * 后台分页查询
     * @param page
     * @return
     */
    @PostMapping("getPageBack")
    public Result getPageBack(@RequestBody Page page) {
        page = commentService.getPageBack(page);
        return new Result(page);
    }

    @Log(title = "删除评论")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) {
        commentService.delete(id);
        return new Result("删除成功！");
    }
}
