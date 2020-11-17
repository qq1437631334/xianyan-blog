package com.wsy.blog.constant;

/**
 * @author wsy
 * @date 2020-08-11 15:54
 */
public interface Constants {

    /**
     *排序方式
     */
    public static final String[] ORDER_BY_MODE = {"asc","desc"};

    /**
     * 博客排序字段数组
     */
    public static final String[] BLOG_ORDER_BY_FIELDS = {"blog_goods","blog_read","blog_type","blog_comment","blog_collection","created_time","update_time"};

    /**
     * 日志排序字段
     */
    public static final String[] LOG_ORDER_BY_FIELDS = {"log_time","created_time"};

    /**
     * 关于我的排序字段
     */
    public static final String[] ABOUT_ORDER_BY_FIELDS = {"about_read","update_time","created_time"};

    /**
     * 音乐排序字段
     */
    public static final String[] MUSIC_ORDER_BY_FIELDS = {"created_time","artist","enable"};


    /**
     * 请求日志分页查询url
     */
    public static final String LOG_PAGE_URL = "/log/getPage";

    /**
     * 限定文件上传大小为10M以内
     */
    long FILE_SIZE_LIMIT = 1024 * 1024 * 10;
}
