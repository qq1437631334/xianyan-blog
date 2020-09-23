package com.wsy.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author wsy
* @date 2020-08-10 10:40
*/

/**
 * 歌曲表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music implements Serializable {
    /**
     * 音乐id
     */
    private Integer id;

    /**
     * 歌曲名
     */
    private String name;

    /**
     * 歌手
     */
    private String artist;

    /**
     * 歌曲链接
     */
    private String url;

    /**
     * 封面
     */
    private String cover;

    /**
     * 歌词
     */
    private String lrc;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GTM+8")
    private Date createdTime;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}