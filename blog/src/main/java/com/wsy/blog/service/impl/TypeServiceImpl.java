package com.wsy.blog.service.impl;

import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.pojo.About;
import com.wsy.blog.pojo.Type;
import com.wsy.blog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.wsy.blog.mapper.TypeMapper;
import com.wsy.blog.service.TypeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        //在插入数据前先查询看是否存在相同数据 如果有则不插入 提示已有相同类型
        Type byName = typeMapper.getByName(type.getTypeName());
        if(null != byName) {
            throw new BlogException(ResultEnum.TYPE_EXIST);
        }
        typeMapper.save(type);
    }

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    @Override
    public List<Type> getAllBack() {
        return typeMapper.getAllBack();
    }

    @Override
    public Type getById(Integer id) {
        return typeMapper.getById(id);
    }

    @Override
    public Type getByName(String name) {
        return typeMapper.getByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(Integer id) {
        //先根据id查询 然后设置enable
        Type byId = typeMapper.getById(id);
        //代表已经是启用状态
        if(StateEnum.ENABLED.getCode().equals(byId.getEnable())) {
            throw new BlogException(ResultEnum.RE_ENABLE);
        }
        byId.setEnable(StateEnum.ENABLED.getCode());
        typeMapper.update(byId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer id) {
        //先根据id查询 然后设置enable
        Type byId = typeMapper.getById(id);
        //代表已经是启用状态
        if(StateEnum.NOT_ENABLE.getCode().equals(byId.getEnable())) {
            throw new BlogException(ResultEnum.RE_DISABLE);
        }
        byId.setEnable(StateEnum.NOT_ENABLE.getCode());
        typeMapper.update(byId);
    }

    @Override
    public Integer getCount() {
        return typeMapper.getCount();
    }

}

