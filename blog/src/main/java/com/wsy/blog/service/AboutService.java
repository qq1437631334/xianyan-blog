package com.wsy.blog.service;

import com.wsy.blog.pojo.About;

import java.util.List;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
public interface AboutService{
    /**
     * 保存方法
     * @param about
     */
    void save(About about);

    /**
     * 根据id查询关于我的
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 根据id删除关于我的
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新关于我的
     * @param about
     */
    void update(About about);

    /**
     * 启用方法
     * @param id
     */
    void enable(Integer id);

    /**
     * 弃用方法
     * @param id
     */
    void disable(Integer id);

    /**
     * 阅读
     * @return
     */
    About read();

    /**
     * 分页查询
     * @return
     */
    List<About> getPage();
}
