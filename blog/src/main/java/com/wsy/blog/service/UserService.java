package com.wsy.blog.service;

import com.wsy.blog.pojo.User;
import com.wsy.blog.utils.Page;

import java.util.List;
import java.util.Map;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
public interface UserService{

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 保存
     * @param user
     */
    void save(User user);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id更新
     * @param user
     */
    void update(User user);

    /**
     * 分页查询
     * @return
     */
    List<User> getPage();

    /**
     * 根据id修改密码
     * @param id
     * @param password
     */
    void updatePassword(Integer id, String password);

    /**
     *  根据用户名查询用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 分页查询收藏
     * @param page
     * @return
     */
    Page getCollectionPage(Page page);

    /**
     * 查询评论数和
     * @return
     */
    Map<String,Object> getCommentAndCollectionCount();

    /**
     * 取消收藏
     * @param id
     */
    void deleteCollection(String id);
}
