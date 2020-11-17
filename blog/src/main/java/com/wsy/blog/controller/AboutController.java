package com.wsy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsy.blog.constant.Constants;
import com.wsy.blog.pojo.About;
import com.wsy.blog.service.AboutService;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.PageUtils;
import com.wsy.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author wsy
 * @date 2020-08-10 20:21
 */
@RestController
@RequestMapping("about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 根据id查询关于我的
     * @param id id
     * @return 结果集
     */
    @GetMapping("getById/{id}")
    public Result<About> getById(@PathVariable Integer id){
        return new Result<>(aboutService.getById(id));
    }

    /**
     * 保存方法
     * @param about 关于我的 实体
     * @return  结果集
     */
    @PostMapping("save")
    public Result<Object> save(@RequestBody About about) {
        aboutService.save(about);
        return new Result<Object>("添加成功！");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("getPage")
    public Result<List<PageInfo>> getPage(@RequestBody Page page){
        PageUtils.checkParams(page, Constants.ABOUT_ORDER_BY_FIELDS);
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderByField() + " " + page.getOrderByMode());
        List<About> list = aboutService.getPage();
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setPages(startPage.getPages());
        pageInfo.setTotal(startPage.getTotal());
        return new Result("查询成功",pageInfo);
    }

    /**
     * 根据id删除关于我的
     * @param id id
     * @return  结果集
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        aboutService.deleteById(id);
        return new Result("删除成功！");
    }

    /**
     * 根据id更新关于我的
     * @param about
     */
    @PutMapping("update")
    public Result update(@RequestBody About about){
        aboutService.update(about);
        return new Result("更新成功！");
    }

    /**
     * 启用方法
     * @param id
     * @return
     */
    @PutMapping("enable/{id}")
    public Result enable(@PathVariable Integer id) {
        aboutService.enable(id);
        return new Result("启用成功");
    }

    /**
     * 弃用方法
     * @param id
     * @return
     */
    @PutMapping("disable/{id}")
    public Result disable(@PathVariable Integer id) {
        aboutService.disable(id);
        return new Result("弃用成功");
    }

    /**
     * 阅读关于我的
     * @return
     */
    @PutMapping("read")
    public Result<About> read() {
        About about = aboutService.read();
        return new Result("阅读成功",about);
    }
}
