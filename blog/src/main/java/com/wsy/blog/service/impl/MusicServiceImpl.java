package com.wsy.blog.service.impl;

import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.pojo.Music;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsy.blog.mapper.MusicMapper;
import com.wsy.blog.service.MusicService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private MusicMapper musicMapper;

    @Override
    public Music getById(Integer id) {
        return musicMapper.getById(id);
    }

    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void deleteById(Integer id) {
        musicMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Music music) {
        musicMapper.update(music);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(Integer id) {
        Music byId = musicMapper.getById(id);
        if(byId.getEnable().equals(StateEnum.ENABLED.getCode())) {
            throw new BlogException(ResultEnum.RE_ENABLE);
        }
        byId.setEnable(StateEnum.ENABLED.getCode());
        musicMapper.update(byId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer id) {
        Music byId = musicMapper.getById(id);
        if(byId.getEnable().equals(StateEnum.NOT_ENABLE.getCode())) {
            throw new BlogException(ResultEnum.RE_DISABLE);
        }
        byId.setEnable(StateEnum.NOT_ENABLE.getCode());
        musicMapper.update(byId);
    }

    @Override
    public List<Music> getPage() {
        return musicMapper.getPage();
    }

    @Override
    public List<Music> getList() {
        return musicMapper.getList();
    }


}

