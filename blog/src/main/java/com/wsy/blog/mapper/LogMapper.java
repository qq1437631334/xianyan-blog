package com.wsy.blog.mapper;

import com.wsy.blog.pojo.Log;import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Mapper
public interface LogMapper {
    /**
     * 保存登录日志方法
     * @param logger
     */
    void save(Log logger);

    /**
     *  根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     * @return
     */
    List<Log> getPage();

    /**
     * 根据id查询一条日志
     * @param id
     * @return
     */
    Log getById(Integer id);

    /**
     * 查询全部
     * @return
     */
    List<Log> getAll();
}