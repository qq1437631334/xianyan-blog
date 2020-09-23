package com.wsy.blog.service;

import com.wsy.blog.pojo.Link;

import java.util.List;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
public interface LinkService{

    /**
     * 保存方法
     * @param link
     */
    void save(Link link);

    /**
     * 前台获得所有友情链接的方法
     * @return
     */
    List<Link> getAll();

    /**
     * 根据id查询友情链接
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 根据name查询友情链接
     * @param name
     * @return
     */
    Link getByName(String name);

    /**
     * 根据id删除友情链接
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新友情链接
     * @param link
     */
    void update(Link link);

}
