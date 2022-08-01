package com.example.springboot.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Files;
import com.example.springboot.mapper.FilesMapper;
import com.example.springboot.service.IFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements IFilesService {
    @Resource
    private FilesMapper filesMapper;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String type = FileUtil.extName(fileName);
        long size = file.getSize();
        //创建uuid作为文件的唯一识别码
        String uuid = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + uuid);
        //判断是否存在文件目录,如果不存在则创建一个
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }
        //当文件存在时再获取md5
        String md5;
        //获取文件的url
        String url;
        //上传文件到磁盘
        file.transferTo(uploadFile);
        //获取md5
        md5 = SecureUtil.md5(uploadFile);
        //从数据库查询是否存在相同文件
        Files dbFile = getFilesByMd5(md5);
        if (dbFile != null) {
            url = dbFile.getUrl();
            //删除已存在的文件
            uploadFile.delete();
        } else {
            //如果不存在文件,则将获取到的文件存到文件目录里去
            url = "http://127.0.0.1:9090/file/" + uuid;
        }


        //再存到数据库
        Files saveFile = new Files();
        saveFile.setName(fileName);
        saveFile.setSize(size / 1024);//转成kb
        saveFile.setType(type);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        filesMapper.insert(saveFile);
        return url;
    }

    @Override
    public void download(String uuid, HttpServletResponse response) throws IOException {
        //获取文件的唯一标识
        File uploadFile = new File(fileUploadPath + uuid);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(uuid, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();

    }

    private Files getFilesByMd5(String md5) {
        //判断是否需要存到磁盘
        QueryWrapper<Files> queryWrapper = new QueryWrapper<Files>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = filesMapper.selectList(queryWrapper);
        return filesList.size()==0 ? null : filesList.get(0);
    }
}
