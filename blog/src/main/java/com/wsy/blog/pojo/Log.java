package com.wsy.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wsy.blog.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author wsy
* @date 2020-08-10 10:40
*/

/**
 * 接口访问日志表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {
    /**
     * 日志id
     */
    @Excel(name = "编号")
    private Integer logId;

    /**
     * 请求路径
     */
    @Excel(name = "请求路径")
    private String logUrl;

    /**
     * 参数
     */
    private String logParams;

    /**
     * 访问状态，1正常0异常
     */
    @Excel(name = "访问状态，1正常0异常")
    private Integer logStatus;

    /**
     * 异常信息
     */
    @Excel(name = "异常信息")
    private String logMessage;

    /**
     * 请求方式，get、post等
     */
    @Excel(name = "请求方式")
    private String logMethod;

    /**
     * 响应时间
     */
    @Excel(name = "响应时间（毫秒）")
    private Long logTime;

    /**
     * 返回值
     */
    private String logResult;

    /**
     * 请求ip
     */
    @Excel(name = "请求ip",autoSize = true)
    private String logIp;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间",autoSize = true)
    @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss",timezone = "GTM+8")
    private Date createdTime;

    /**
     * 创建人
     */
    private String createdBy;

    private static final long serialVersionUID = 1L;
}