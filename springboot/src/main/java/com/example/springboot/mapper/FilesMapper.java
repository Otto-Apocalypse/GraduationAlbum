package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Files;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesMapper extends BaseMapper<Files> {
}
