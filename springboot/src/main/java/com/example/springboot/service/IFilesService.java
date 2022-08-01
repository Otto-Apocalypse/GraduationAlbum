package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Files;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IFilesService extends IService<Files> {
    String upload(MultipartFile file) throws IOException;

    void download(String uuid, HttpServletResponse response) throws IOException;
}
