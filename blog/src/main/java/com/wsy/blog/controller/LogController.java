package com.wsy.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsy.blog.constant.Constants;
import com.wsy.blog.pojo.Log;
import com.wsy.blog.service.LogService;
import com.wsy.blog.utils.Page;
import com.wsy.blog.utils.PageUtils;
import com.wsy.blog.utils.Result;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wsy
 * @date 2020-08-11 15:21
 */
@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("getPage")
    public Result<PageInfo<Log>> getPage(@RequestBody Page page){
        PageUtils.checkParams(page, Constants.LOG_ORDER_BY_FIELDS);
        //设置当前页面和每页条数
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderByField() + " " + page.getOrderByMode());
        List<Log> list = logService.getPage();
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setTotal(startPage.getTotal());
        pageInfo.setPages(startPage.getPages());
        return new Result<>("查询成功！",pageInfo);
    }

    /**
     * 根据id查询日志
     * @param id
     * @return
     */
    @GetMapping("getById/{id}")
    public Result<Log> getById(@PathVariable Integer id){
        return new Result<>(logService.getById(id));
    }

    /**
     * 根据id删除日志
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        logService.deleteById(id);
        return new Result("删除成功！");
    }

    /**
     * 根据ids删除日志
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Integer> ids){
        logService.deleteByIds(ids);
        return new Result("删除成功");
    }

    /**
     * 导出所有日志
     */
    @GetMapping("export")
    public void export(HttpServletResponse response) throws Exception{
        Workbook workbook = logService.export();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "日志");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
