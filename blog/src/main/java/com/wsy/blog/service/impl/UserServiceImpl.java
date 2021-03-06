package com.wsy.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wsy.blog.config.ShiroFilterConfig;
import com.wsy.blog.dao.CollectionDao;
import com.wsy.blog.dao.CommentDao;
import com.wsy.blog.dao.CommentGoodDao;
import com.wsy.blog.dao.GoodDao;
import com.wsy.blog.mapper.BlogMapper;
import com.wsy.blog.pojo.*;
import com.wsy.blog.utils.Md5Utils;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.ShiroUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.wsy.blog.mapper.UserMapper;
import com.wsy.blog.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    private final CollectionDao collectionDao;

    private final CommentDao commentDao;

    private final GoodDao goodDao;

    private final CommentGoodDao commentGoodDao;

    private final BlogMapper blogMapper;

    private final ShiroFilterConfig shiroFilterConfig;

    public UserServiceImpl(CollectionDao collectionDao, CommentDao commentDao, GoodDao goodDao, CommentGoodDao commentGoodDao, BlogMapper blogMapper, ShiroFilterConfig shiroFilterConfig) {
        this.collectionDao = collectionDao;
        this.commentDao = commentDao;
        this.goodDao = goodDao;
        this.commentGoodDao = commentGoodDao;
        this.blogMapper = blogMapper;
        this.shiroFilterConfig = shiroFilterConfig;
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void save(User user) {
        user.setSalt(Md5Utils.createSalt());
        user.setPassword(Md5Utils.md5(user.getPassword(),user.getSalt(),this.shiroFilterConfig.getHashIterations()));
        user.setCreatedTime(DateUtil.date());
        userMapper.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        //在用户更新后 将评论 点赞 收藏 评论点赞里的user都更新
        userMapper.update(user);
        //评论
        List<Comment> comments = commentDao.getByCommentUserId(user.getUserId());
        for (Comment comment : comments) {
            comment.setCommentUser(user);
            commentDao.save(comment);
        }
        //点赞
        List<Good> goods = goodDao.getByUserId(user.getUserId());
        for (Good good : goods) {
            good.setUser(user);
            goodDao.save(good);
        }
        //收藏
        List<Collection> collections = collectionDao.getByUserId(user.getUserId());
        for (Collection collection : collections) {
            collection.setUser(user);
            collectionDao.save(collection);
        }
        //评论点赞
        List<CommentGood> commentGoods = commentGoodDao.getByUserId(user.getUserId());
        for (CommentGood commentGood : commentGoods) {
            commentGood.setUser(user);
            commentGoodDao.save(commentGood);
        }
    }

    @Override
    public List<User> getPage() {
        return userMapper.getPage();
    }

    @Override
    public void updatePassword(Integer id, String password) {
        User user = this.userMapper.getById(id);
        //设置盐
        user.setSalt(Md5Utils.createSalt());
        //设置密码
        user.setPassword(Md5Utils.md5(password, user.getSalt(), this.shiroFilterConfig.getHashIterations()));
        userMapper.update(user);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public Page<Collection> getCollectionPage(Page page) {
        Collection collection = new Collection();
        User user = (User) ShiroUtils.getLoginUser();
        collection.setUserId(user.getUserId());
        Example<Collection> example = Example.of(collection);
        Pageable pageable = PageRequest.of(page.getPageNum() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Collection> all = collectionDao.findAll(example, pageable);
        page.setData(all.getContent());
        page.setTotalCount((int) all.getTotalElements());
        page.setTotalPage(all.getTotalPages());
        return page;
    }

    @Override
    public Map<String, Object> getCommentAndCollectionCount() {
        if (ShiroUtils.getLoginUser() instanceof Admin) {
            return null;
        }
        User user = (User) ShiroUtils.getLoginUser();
        int collectionCount = collectionDao.countByUserId(user.getUserId());
        Map<String, Object> map = new HashMap<>(8);
        map.put("collectionCount", collectionCount);
        int commentCount = commentDao.countByCommentUserId(user.getUserId());
        map.put("commentCount", commentCount);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCollection(String id) {
        Collection collection = new Collection();
        collection.setId(id);
        Collection collectionById = collectionDao.getById(id);
        blogMapper.reduceCollection(collectionById.getBlogId());
        collectionDao.delete(collection);
    }
}

