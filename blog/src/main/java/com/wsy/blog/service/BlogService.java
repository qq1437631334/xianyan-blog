package com.wsy.blog.service;

import com.wsy.blog.pojo.Blog;
import com.wsy.blog.pojo.Collection;
import com.wsy.blog.pojo.Good;
import com.wsy.blog.utils.Page;
import com.wsy.blog.vo.BlogVo;

import java.util.List;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
public interface BlogService{

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
    BlogVo getById(String id);

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
     * @param page
     * @return
     */
    List<BlogVo> getPage(Page page);

    /**
     * 阅读博客
     * @param id    博客id
     * @return  博客传输对象
     */
    BlogVo read(String id);

    /**
     * 获得推荐阅读
     * @return
     */
    List<BlogVo> recommendedRead();

    /**
     * 获得时间轴
     * @return
     */
    List<BlogVo> getTimeLine();

    /**
     * 点赞
     * @param good
     */
    void good(Good good);

    /**
     * 查询当前用户当前博客是否已经点赞
     * @param blogId    博客id
     * @return  boolean
     */
    boolean getIsGood(String blogId);

    /**
     * 收藏
     * @param collection
     */
    void collection(Collection collection);

    /**
     * 查询当前用户当前博客是否已经收藏
     * @param blogId
     * @return
     */
    boolean getIsCollection(String blogId);

}
