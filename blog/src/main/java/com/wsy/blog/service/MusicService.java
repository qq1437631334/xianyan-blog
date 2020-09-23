package com.wsy.blog.service;

import com.wsy.blog.pojo.Music;

import java.util.List;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
public interface MusicService{

    /**
     * 根据id查询音乐
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 保存音乐
     * @param music
     */
    void save(Music music);

    /**
     * 根据id删除音乐
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id更新音乐
     * @param music
     */
    void update(Music music);

    /**
     * 启用音乐
     * @param id
     */
    void enable(Integer id);

    /**
     * 弃用音乐
     * @param id
     */
    void disable(Integer id);

    /**
     * 分页查询
     * @return
     */
    List<Music> getPage();

    /**
     * 得到最新的十首音乐
     * @return
     */
    List<Music> getList();
}
