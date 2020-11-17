package com.wsy.blog.utils;

import com.wsy.blog.constant.Constants;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.exception.BlogException;

import java.util.Arrays;
import java.util.List;

/**
 * @author wsy
 * @date 2020-08-12 14:13
 */
public class PageUtils {

    /**
     * 校验前台传入的参数
     * @param page
     */
    public static void checkParams(Page page, String[] fields){
        List<String> fieldList = Arrays.asList(fields);
        List<String> modeList = Arrays.asList(Constants.ORDER_BY_MODE);
        if(1 > page.getPageNum() || page.getPageSize() < 1 || !modeList.contains(page.getOrderByMode().toLowerCase())
        || !fieldList.contains(page.getOrderByField().toLowerCase())) {
            throw new BlogException(ResultEnum.PARAMS_ERROR);
        }
    }


}
