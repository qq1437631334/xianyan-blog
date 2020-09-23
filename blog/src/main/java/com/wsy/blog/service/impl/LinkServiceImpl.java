package com.wsy.blog.service.impl;

import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.mapper.LinkMapper;
import com.wsy.blog.pojo.Link;
import javax.annotation.Resource;
import com.wsy.blog.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public void save(Link link) {
        linkMapper.save(link);
    }

    @Override
    public List<Link> getAll() {
        return linkMapper.getAll();
    }

    @Override
    public Link getById(Integer id) {
        return linkMapper.getById(id);
    }

    @Override
    public Link getByName(String name) {
        return linkMapper.getByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        linkMapper.deleteById(id);
    }

    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }
}

