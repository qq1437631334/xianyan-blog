package com.wsy.blog.service.impl;

import com.wsy.blog.excel.entity.ExportParams;
import com.wsy.blog.excel.handler.ExcelExportHandler;
import com.wsy.blog.mapper.LogMapper;
import com.wsy.blog.pojo.Log;
import com.wsy.blog.service.LogService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * @author wsy
 * @date 2020-08-10 10:39
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void save(Log log){
        logMapper.save(log);
    }

    
    @Override
    public Log getById(Integer id) {
        return logMapper.getById(id);
    }


    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    @Override
    public List<Log> getPage() {
        List<Log> page = logMapper.getPage();
        return page;
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }


    @Override
    public Workbook export() {
        List<Log> logList = logMapper.getAll();
        return new ExcelExportHandler().createSheet(new ExportParams("最新日志", "sheet1"), Log.class, logList);
    }
}

