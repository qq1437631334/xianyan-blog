package com.wsy.blog.mapper;

import com.wsy.blog.pojo.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:40
 */
@Mapper
public interface MusicMapper {

    /**
     * 保存音乐
     * @param music
     */
    void save(Music music);

    /**
     * 根据id取得音乐
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新音乐
     * @param music
     */
    void update(Music music);

    /**
     * 分页查询
     * @return
     */
    List<Music> getPage();

    /**
     * 获得最新的十首启用的音乐
     * @return
     */
    List<Music> getList();
}