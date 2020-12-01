package com.wsy.blog.mapper;

import com.wsy.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Mapper
public interface UserMapper {

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
     * 根据用户名查询
     * @param username
     * @return
     */
    User getByUsername(String username);

}