package com.wsy.blog.dao;

import com.wsy.blog.pojo.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-17 12:53
 */
public interface CollectionDao extends MongoRepository<Collection,String> {

    /**
     * 根据博客id和用户id查询
     * @param blogId
     * @param userId
     * @return
     */
    int countByBlogIdAndUserId(String blogId, Integer userId);

    /**
     * 根据用户id查询收藏数
     * @param userId
     * @return
     */
    int countByUserId(Integer userId);

    /**
     * 根据用户id查询收藏
     * @param userId
     * @return
     */
    List<Collection> getByUserId(Integer userId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Collection getById(String id);
}
