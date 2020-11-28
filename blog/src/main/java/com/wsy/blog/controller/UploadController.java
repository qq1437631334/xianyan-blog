package com.wsy.blog.controller;

import com.wsy.blog.annotation.Log;
import com.wsy.blog.constant.Constants;
import com.wsy.blog.enums.ResultEnum;
import com.wsy.blog.utils.Result;
import com.wsy.blog.utils.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Log(title = "上传图片")
    @PostMapping("uploadImage")
    public Result<?> uploadImage(MultipartFile file, String uploadUri) {
        if (uploadUri == null) {
            uploadUri = Constants.DEFAULT_IMG_UPLOAD_URI;
        }
        String url = uploadService.upload(file, uploadUri);
        if (StringUtils.isNotBlank(url)) {
            return new Result<>("上传成功！", url);
        }
        return new Result<>(ResultEnum.ERROR);
    }
}
