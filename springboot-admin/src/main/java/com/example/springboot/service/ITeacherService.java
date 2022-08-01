package com.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Teacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-29
 */
public interface ITeacherService extends IService<Teacher> {
    IPage<Teacher> findPage(Integer pageNum, Integer pageSize, String optionValue,String username);

    Teacher findUser(String username);
}
