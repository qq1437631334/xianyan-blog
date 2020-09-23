package com.wsy.blog.mapper;

import com.wsy.blog.pojo.Blog;
import com.wsy.blog.utils.Page;
import com.wsy.blog.vo.BlogVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Mapper
public interface BlogMapper {
    /**
     * 保存方法
     * @param blog
     */
    void save(Blog blog);

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 根据id删除博客
     * @param id
     */
    void deleteById(String id);

    /**
     * 更新博客
     * @param blog
     */
    void update(Blog blog);

    /**
     * 分页查询博客
     * @return
     */
    List<Blog> getPage(Page page);


    /**
     * 获得推荐阅读
     * @return
     */
    List<Blog> recommendedRead();

    /**
     * 获得时间轴
     * @return
     */
    List<BlogVo> getTimeLine();

    /**
     * 点赞
     * @param blogId
     */
    void good(String blogId);

    /**
     * 评论
     * @param commentBlogId
     */
    void comment(String commentBlogId);

    /**
     * 收藏
     * @param blogId
     */
    void collection(String blogId);

    /**
     * 减少评论数
     * @param commentBlogId
     */
    void reduceComment(String commentBlogId);

    /**
     * 减少收藏数
     * @param blogId
     */
    void reduceCollection(String blogId);
}