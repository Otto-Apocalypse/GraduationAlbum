package com.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.PasswordDTO;
import com.example.springboot.controller.dto.TeacherDTO;
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

    TeacherDTO login(TeacherDTO teacherDTO);

    Teacher findUser(String username);

    void updatePassword(PasswordDTO passwordDTO);
}
