package com.wsy.blog.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wsy
 * @date 2020-08-11 21:52
 */
@Data
public class Page<T> implements Serializable {

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 一页获取多少条数据
     */
    private Integer pageSize;

    /**
     * 排序字段
     */
    private String orderByField;

    /**
     * 排序方式
     */
    private String orderByMode;

     /**
     * 类型
     */
    private Integer typeId;

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 总条数
     */
    private Integer totalCount;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 条件参数
     */
    private Map<String, Object> params = new HashMap<>(16);



    public void setOrderByField(String orderByField) {
        this.orderByField = StringUtils.upperCharToUnderLine(orderByField);
    }
}
