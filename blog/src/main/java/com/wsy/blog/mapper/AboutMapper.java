package com.wsy.blog.mapper;

import com.wsy.blog.pojo.About;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Mapper
public interface AboutMapper {
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
     * 获得一个启用了的关于我的
     * @return
     */
    About getOne();

    /**
     * 分页查询
     * @return
     */
    List<About> getPage();

    /**
     * 获得启用的关于列表
     * @return  启动的关于列表
     */
    List<About> getByEnable();
}