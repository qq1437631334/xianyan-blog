package com.wsy.blog.mapper;

import com.wsy.blog.pojo.Type;
import com.wsy.blog.utils.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Mapper
public interface TypeMapper {
    /**
     * 保存方法
     * @param type
     */
    void save(Type type);

    /**
     * 前台获得所有类型的方法（启用了的）
     * @return
     */
    List<Type> getAll();

    /**
     * 获得所有类型的方法
     * @return
     */
    List<Type> getAllBack();

    /**
     * 根据id查询类型
     * @param id
     * @return
     */
    Type getById(Integer id);

    /**
     * 根据名字查询类型
     * @param name
     * @return
     */
    Type getByName(String name);

    /**
     * 根据id删除类型
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新类型
     * @param type
     */
    void update(Type type);

    /**
     * 根据id获取类型名字
     * @param id
     * @return
     */
    String getNameById(Integer id);

    /**
     * 获取已开启的类型条数
     * @return
     */
    Integer getCount();
}