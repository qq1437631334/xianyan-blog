package com.wsy.blog.service.impl;

import com.wsy.blog.dao.CollectionDao;
import com.wsy.blog.dao.GoodDao;
import com.wsy.blog.mapper.TypeMapper;
import com.wsy.blog.pojo.*;
import com.wsy.blog.utils.IdWorker;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.ShiroUtils;
import com.wsy.blog.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsy.blog.mapper.BlogMapper;
import com.wsy.blog.service.BlogService;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class BlogServiceImpl implements BlogService {


    @Resource
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(cacheNames = "blog", key = "#blog.blogId")
    public void save(Blog blog) {
        blog.setBlogId(idWorker.nextId()+"");
        blogMapper.save(blog);
        Type oldType = typeMapper.getById(blog.getBlogType());
        oldType.setTypeBlogCount(oldType.getTypeBlogCount() + 1);
        typeMapper.update(oldType);
    }



    @Override
    @Cacheable(cacheNames = "blog", key = "#id")
    public BlogVo getById(String id) {
        //先根据id查询出Blog
        Blog byId = blogMapper.getById(id);
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(byId,blogVo);
        String typeName = typeMapper.getNameById(blogVo.getBlogType());
        blogVo.setTypeName(typeName);
        return blogVo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "blog", key = "#id")
    public void deleteById(String id) {
        Blog oldBlog = blogMapper.getById(id);
        blogMapper.deleteById(id);
        Type oldType = typeMapper.getById(oldBlog.getBlogType());
        oldType.setTypeBlogCount(oldType.getTypeBlogCount() - 1);
        typeMapper.update(oldType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(cacheNames = "blog", key = "#blog.blogId")
    public void update(Blog blog) {
        Blog oldBlog = blogMapper.getById(blog.getBlogId());
        blogMapper.update(blog);
        //代表改变了类型
        if(!oldBlog.getBlogType().equals(blog.getBlogType())) {
            Type oldBlogType = typeMapper.getById(oldBlog.getBlogType());
            oldBlogType.setTypeBlogCount(oldBlogType.getTypeBlogCount() - 1);
            typeMapper.update(oldBlogType);
            Type updateBlogType = typeMapper.getById(blog.getBlogType());
            updateBlogType.setTypeBlogCount(updateBlogType.getTypeBlogCount() + 1);
            typeMapper.update(updateBlogType);
        }
    }

    @Override
    public List<BlogVo> getPage(Page page) {
        List<Blog> blogs = blogMapper.getPage(page);
        List<BlogVo> blogVo = new ArrayList<>();
        //将Blog 的属性赋值到 BlogVo
        for (Blog blog : blogs) {
            BlogVo item = new BlogVo();
            BeanUtils.copyProperties(blog,item);
            //设置类型名
            String typeName = typeMapper.getNameById(item.getBlogType());
            item.setTypeName(typeName);
            blogVo.add(item);
        }
        return blogVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo read(String id) {
        //先根据id查询出博客信息 然后阅读数+1
        BlogVo byId = this.getById(id);
        byId.setBlogRead(byId.getBlogRead() + 1);
        blogMapper.update(byId);
        return byId;
    }

    @Override
    public List<BlogVo> recommendedRead() {
        List<Blog> list = blogMapper.recommendedRead();
        List<BlogVo> blogVos = new ArrayList<>();
        for (Blog blog : list) {
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(blog,blogVo);
            String typeName = typeMapper.getNameById(blogVo.getBlogType());
            blogVo.setTypeName(typeName);
            blogVos.add(blogVo);
        }
        return blogVos;
    }

    @Override
    public List<BlogVo> getTimeLine() {
        return blogMapper.getTimeLine();
    }

    @Override
    public void good(Good good) {
        blogMapper.good(good.getBlogId());
        //存储点赞信息到mongodb
        good.setBlog(blogMapper.getById(good.getBlogId()));
        User loginUser = (User) ShiroUtils.getLoginUser();
        good.setUserId(loginUser.getUserId());
        good.setUser(loginUser);
        good.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()));
        goodDao.save(good);
    }

    @Override
    public boolean getIsGood(String blogId) {
        User loginUser = (User) ShiroUtils.getLoginUser();
        int count = goodDao.countByBlogIdAndUserId(blogId, loginUser.getUserId());
        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void collection(Collection collection) {
        blogMapper.collection(collection.getBlogId());
        //存储收藏信息到mongodb
        collection.setBlog(blogMapper.getById(collection.getBlogId()));
        User loginUser = (User) ShiroUtils.getLoginUser();
        collection.setUserId(loginUser.getUserId());
        collection.setUser(loginUser);
        collection.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()));
        collectionDao.save(collection);
    }

    @Override
    public boolean getIsCollection(String blogId) {
        User loginUser = (User) ShiroUtils.getLoginUser();
        int count = collectionDao.countByBlogIdAndUserId(blogId, loginUser.getUserId());
        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }
}

