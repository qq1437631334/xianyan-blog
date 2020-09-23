package com.wsy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsy.blog.constant.Const;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.exception.BlogException;
import com.wsy.blog.pojo.Music;
import com.wsy.blog.service.MusicService;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.PageUtils;
import com.wsy.blog.utils.Result;
import com.wsy.blog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * @author wsy
 * @date 2020-08-10 20:21
 */
@RestController
@RequestMapping("music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    /**
     * 根据id查询音乐
     * @param id id
     * @return 结果集
     */
    @GetMapping("getById/{id}")
    public Result<Music> getById(@PathVariable Integer id){
        return new Result<>(musicService.getById(id));
    }

    /**
     * 保存方法
     * @param music 音乐 实体
     * @return  结果集
     */
    @PostMapping("save")
    public Result<Object> save(@RequestBody Music music) {
        musicService.save(music);
        return new Result<Object>("添加成功！");
    }

    /**
     * 根据id删除音乐
     * @param id id
     * @return  结果集
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        musicService.deleteById(id);
        return new Result("删除成功！");
    }

    /**
     * 根据id更新音乐
     * @param music
     */
    @PutMapping("update")
    public Result update(@RequestBody Music music){
        musicService.update(music);
        return new Result("更新成功！");
    }

    /**
     * 启用方法
     * @param id
     * @return
     */
    @PutMapping("enable/{id}")
    public Result enable(@PathVariable Integer id) {
        musicService.enable(id);
        return new Result("启用成功");
    }

    /**
     * 弃用方法
     * @param id
     * @return
     */
    @PutMapping("disable/{id}")
    public Result disable(@PathVariable Integer id) {
        musicService.disable(id);
        return new Result("弃用成功");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("getPage")
    public Result<PageInfo<Music>> getPage(@RequestBody Page page){
        PageUtils.checkParams(page,Const.MUSIC_ORDER_BY_FIELDS);
        //设置当前页面和每页条数
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderByField() + " " + page.getOrderByMode());
        List<Music> list = musicService.getPage();
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setTotal(startPage.getTotal());
        pageInfo.setPages(startPage.getPages());
        return new Result<>("查询成功！",pageInfo);
    }

    @GetMapping("getList")
    public Result getList(){
        List<Music> list = musicService.getList();
        return new Result(list);
    }
}
