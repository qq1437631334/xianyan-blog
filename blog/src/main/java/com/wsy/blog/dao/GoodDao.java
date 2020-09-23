package com.wsy.blog.dao;

import com.wsy.blog.pojo.Good;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-17 12:53
 */
public interface GoodDao extends MongoRepository<Good,String> {

    /**
     * 根据博客id和用户id查询
     * @param blogId
     * @param userId
     * @return
     */
    int countByBlogIdAndUserId(String blogId,Integer userId);

    /**
     * 根据用户id查询点赞
     * @param userId
     * @return
     */
    List<Good> getByUserId(Integer userId);
}
