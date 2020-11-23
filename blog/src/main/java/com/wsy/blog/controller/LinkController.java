package com.wsy.blog.controller;

import com.wsy.blog.annotation.Log;
import com.wsy.blog.pojo.Link;
import com.wsy.blog.service.LinkService;
import com.wsy.blog.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 20:21
 */
@RestController
@RequestMapping("link")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    /**
     * 前台获得所有友情链接的方法
     * @return
     */
    @GetMapping("getAll")
    public Result<List<Link>> getAll(){
        return new Result<>(linkService.getAll());
    }

    /**
     * 根据id查询友情链接
     * @param id
     * @return
     */
    @GetMapping("getById/{id}")
    public Result<Link> getById(@PathVariable Integer id){
        return new Result<>(linkService.getById(id));
    }

    /**
     * 根据name查询友情链接
     * @param name
     * @return
     */
    @GetMapping("getByName/{name}")
    public Result<Link> getByName(@PathVariable String name){
        return new Result<>(linkService.getByName(name));
    }

    /**
     * 保存方法
     * @param link
     * @return
     */
    @Log(title = "保存友情链接")
    @PostMapping("save")
    public Result save(@RequestBody Link link) {
        linkService.save(link);
        return new Result("添加成功！");
    }

    /**
     * 根据id删除友情链接
     * @param id
     * @return
     */
    @Log(title = "删除友情链接")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        linkService.deleteById(id);
        return new Result("删除成功！");
    }

    /**
     * 根据id更新友情链接
     * @param link
     */
    @Log(title = "更新友情链接")
    @PutMapping("update")
    public Result update(@RequestBody Link link){
        linkService.update(link);
        return new Result("更新成功！");
    }

}
