package com.wsy.blog.controller;

import com.wsy.blog.utils.Result;
import com.wsy.blog.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wsy
 * @date 2020-08-13 15:41
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping("uploadImage")
    public Result uploadImage(MultipartFile file){
        String url = uploadService.uploadImage(file);
        return new Result("上传成功！",url);
    }
}
