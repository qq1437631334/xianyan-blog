package com.wsy.blog.controller;

import com.wsy.blog.annotation.Log;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.pojo.Type;
import com.wsy.blog.service.TypeService;
import com.wsy.blog.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 20:21
 */
@RestController
@RequestMapping("type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * 前台获得所有类型的方法（启用了的）
     * @return
     */
    @GetMapping("getAll")
    public Result<List<Type>> getAll(){
        return new Result<>(typeService.getAll());
    }

    /**
     * 获取已开启的类型条数
     * @return
     */
    @GetMapping("getCount")
    public Result getCount(){
        return new Result(ResultEnum.SUCCESS,typeService.getCount());
    }

    /**
     * 获得所有类型的方法
     * @return
     */
    @GetMapping("getAllBack")
    public Result<List<Type>> getAllBack(){
        return new Result<>(typeService.getAllBack());
    }

    /**
     * 根据id查询类型
     * @param id
     * @return
     */
    @GetMapping("getById/{id}")
    public Result<Type> getById(@PathVariable Integer id){
        return new Result<>(typeService.getById(id));
    }

    /**
     * 根据name查询类型
     * @param name
     * @return
     */
    @GetMapping("getByName/{name}")
    public Result<Type> getByName(@PathVariable String name){
        return new Result<>(typeService.getByName(name));
    }

    /**
     * 保存方法
     * @param type
     * @return
     */
    @Log(title = "保存类型")
    @PostMapping("save")
    public Result save(@RequestBody Type type) {
        typeService.save(type);
        return new Result("添加成功！");
    }

    /**
     * 根据id删除类型
     * @param id
     * @return
     */
    @Log(title = "删除类型")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        typeService.deleteById(id);
        return new Result("删除成功！");
    }

    /**
     * 根据id更新类型
     * @param type
     */
    @Log(title = "更新类型")
    @PutMapping("update")
    public Result update(@RequestBody Type type){
        typeService.update(type);
        return new Result("更新成功！");
    }

    /**
     * 启用方法
     * @param id
     * @return
     */
    @Log(title = "启用类型")
    @PutMapping("enable/{id}")
    public Result enable(@PathVariable Integer id) {
        typeService.enable(id);
        return new Result("启用成功");
    }

    /**
     * 弃用方法
     * @param id
     * @return
     */
    @Log(title = "弃用类型")
    @PutMapping("disable/{id}")
    public Result disable(@PathVariable Integer id) {
        typeService.disable(id);
        return new Result("弃用成功");
    }

}
