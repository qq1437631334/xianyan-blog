package com.wsy.blog.service.impl;

import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.mapper.AboutMapper;
import com.wsy.blog.pojo.About;
import javax.annotation.Resource;
import com.wsy.blog.service.AboutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class AboutServiceImpl implements AboutService {

    @Resource
    private AboutMapper aboutMapper;

    @Override
    public void save(About about) {
        aboutMapper.save(about);
    }

    @Override
    public About getById(Integer id) {
        return aboutMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        aboutMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(About about) {
        aboutMapper.update(about);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(Integer id) {
        //先根据id查询 然后设置enable
        About byId = aboutMapper.getById(id);
        List<About> byEnable = aboutMapper.getByEnable();
        if(byEnable.size() > 0) {
            throw new BlogException(ResultEnum.EXIST_ENABLE);
        }
        //代表已经是启用状态
        if(StateEnum.ENABLED.getCode().equals(byId.getEnable())) {
            throw new BlogException(ResultEnum.RE_ENABLE);
        }
        byId.setEnable(StateEnum.ENABLED.getCode());
        aboutMapper.update(byId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer id) {
        //先根据id查询 然后设置enable
        About byId = aboutMapper.getById(id);
        //代表已经是启用状态
        if(StateEnum.NOT_ENABLE.getCode().equals(byId.getEnable())) {
            throw new BlogException(ResultEnum.RE_DISABLE);
        }
        byId.setEnable(StateEnum.NOT_ENABLE.getCode());
        aboutMapper.update(byId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public About read() {
        /**
         * 获得一个启用的关于我的
         * 一边情况下只有一个启动的
         */
        About one = aboutMapper.getOne();
        if(null == one) {
            throw new BlogException("目前暂时没有关于我的！");
        }
        one.setAboutRead(one.getAboutRead() + 1);
        aboutMapper.update(one);
        return one;
    }

    @Override
    public List<About> getPage() {
        return aboutMapper.getPage();
    }

}

