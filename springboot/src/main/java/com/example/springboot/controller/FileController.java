package com.example.springboot.controller;

import com.example.springboot.service.IFilesService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件上传相关接口
 * 直接返回文件地址String类型,不再封装到Result类中,因为前端拿到之后要直接用
 */

@RestController
@RequestMapping("/file")
@Api(tags = "文件操作")
public class FileController {

    @Resource
    private IFilesService filesService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return filesService.upload(file);
    }

    @GetMapping("/{uuid}")
    public void download(@PathVariable String uuid, HttpServletResponse response) throws IOException {
        filesService.download(uuid,response);
    }

}
