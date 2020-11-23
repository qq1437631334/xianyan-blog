package com.wsy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsy.blog.annotation.Log;
import com.wsy.blog.constant.Constants;
import com.wsy.blog.pojo.Blog;
import com.wsy.blog.pojo.Collection;
import com.wsy.blog.pojo.Good;
import com.wsy.blog.service.BlogService;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.PageUtils;
import com.wsy.blog.utils.Result;
import com.wsy.blog.vo.BlogVo;
import com.wsy.blog.vo.TimeLineVo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wsy
 * @date 2020-08-11 15:21
 */
@RestController
@RequestMapping("blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("getPage")
    public Result<PageInfo<BlogVo>> getPage(@RequestBody Page page){
        PageUtils.checkParams(page, Constants.BLOG_ORDER_BY_FIELDS);
        //设置当前页面和每页条数
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderByField() + " " + page.getOrderByMode());
        List<BlogVo> list = blogService.getPage(page);
        page.setData(list);
        page.setTotalCount((int)startPage.getTotal());
        page.setTotalPage(startPage.getPages());
        return new Result("查询成功！",page);
    }

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @GetMapping("getById/{id}")
    public Result<BlogVo> getById(@PathVariable String id){
        return new Result<>(blogService.getById(id));
    }


    /**
     * 保存方法
     * @param blog
     * @return
     */
    @Log(title = "保存博客")
    @PostMapping("save")
    public Result save(@RequestBody Blog blog) {
        blogService.save(blog);
        return new Result("添加成功！");
    }

    /**
     * 根据id删除博客
     * @param id
     * @return
     */
    @Log(title = "删除博客")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) {
        blogService.deleteById(id);
        return new Result("删除成功！");
    }

    /**
     * 根据id更新博客
     * @param blog
     */
    @Log(title = "更新博客")
    @PutMapping("update")
    public Result update(@RequestBody Blog blog){
        blogService.update(blog);
        return new Result("更新成功！");
    }

    /**
     * 阅读博客
     * @param id
     * @return
     */
    @Log(title = "阅读博客")
    @PutMapping("read/{id}")
    public Result<BlogVo> read(@PathVariable String id) {
        BlogVo blogVo = blogService.read(id);
        return new Result("阅读成功",blogVo);
    }

    /**
     * 获得推荐阅读
     * @return
     */
    @GetMapping("recommendedRead")
    public Result<BlogVo> recommendedRead(){
        List<BlogVo> list = blogService.recommendedRead();
        return new Result("获取成功",list);
    }


    /**
     * 查询时间轴
     *
     * @return
     */
    @Log(title = "查询时间轴")
    @GetMapping("getTimeLine")
    public Result<List<TimeLineVo>> getTimeLine() {
        List<TimeLineVo> timeList = new ArrayList<>(16);
        List<BlogVo> blogVoList = blogService.getTimeLine();
        blogVoList.forEach(e -> {
            String blogMonth = e.getBlogMonth();
            TimeLineVo timeLineVo = new TimeLineVo();
            timeLineVo.setMonth(blogMonth);
            if(timeList.contains(timeLineVo)) {
                // 取出对应的数据
                TimeLineVo timeLine = getTimeLineForList(timeList, timeLineVo);
                List<BlogVo> list = timeLine.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLine.setList(list);
            } else {
                List<BlogVo> list = timeLineVo.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLineVo.setList(list);
                timeList.add(timeLineVo);
            }
        });
        return new Result<>(timeList);
    }

    /**
     * 点赞
     * @param good
     * @return
     */
    @Log(title = "点赞")
    @PostMapping("good")
    public Result good(@RequestBody Good good) {
        blogService.good(good);
        return new Result("点赞成功");
    }

    /**
     * 根据博客id和用户id查询该博客是否已经点赞
     * @param blogId
     * @return
     */
    @GetMapping("getIsGood/{blogId}")
    public Result getIsGood(@PathVariable String blogId) {
        boolean isGood = blogService.getIsGood(blogId);
        return new Result(isGood);
    }

    /**
     * 收藏
     * @param collection
     * @return
     */
    @Log(title = "收藏博客")
    @PostMapping("collection")
    public Result collection(@RequestBody Collection collection) {
        blogService.collection(collection);
        return new Result("收藏成功");
    }

    /**
     * 根据博客id和用户id查询该博客是否已经收藏
     * @param blogId
     * @return
     */
    @GetMapping("getIsCollection/{blogId}")
    public Result getIsCollection(@PathVariable String blogId) {
        boolean isCollection = blogService.getIsCollection(blogId);
        return new Result(isCollection);
    }


    /**
     * 获取对应的timeLine
     * @param timeList
     * @param timeLineVo
     * @return
     */
    private TimeLineVo getTimeLineForList(List<TimeLineVo> timeList, TimeLineVo timeLineVo) {
        for (TimeLineVo lineVo : timeList) {
            if(timeLineVo.getMonth().equals(lineVo.getMonth())) {
                return lineVo;
            }
        }
        return null;
    }


}
