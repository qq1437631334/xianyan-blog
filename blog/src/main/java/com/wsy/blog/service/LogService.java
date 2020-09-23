package com.wsy.blog.service;

import com.wsy.blog.pojo.Log;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
* @author wsy
* @date 2020-08-10 10:40
*/
public interface LogService{
    /**
     * 保存日志方法
     * @param log
     */
    void save(Log log);

    /**
     * 根据id查询日志
     * @param id
     * @return
     */
    Log getById(Integer id);

    /**
     * 根据id删除日志
     * @param id
     */
    void deleteById(Integer id);



    /**
     * 分页查询日志
     * @return
     */
    List<Log> getPage();

    /**
     * 根据ids删除日志
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 导出全部
     * @return
     */
    Workbook export();
}
