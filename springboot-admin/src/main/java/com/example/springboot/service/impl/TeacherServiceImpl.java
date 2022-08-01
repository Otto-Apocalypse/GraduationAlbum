package com.example.springboot.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Teacher;
import com.example.springboot.mapper.TeacherMapper;
import com.example.springboot.service.ITeacherService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-29
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    private static final Log LOG = Log.get();

    public IPage<Teacher> findPage(Integer pageNum, Integer pageSize, String optionValue,String username){
        IPage<Teacher> iPage = new Page<>(pageNum,pageSize);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>();
        switch (optionValue){
            case "username":
                queryWrapper.like("username",username);
                break;
            case "name":
                queryWrapper.like("name",username);
                break;
            case "subject":
                queryWrapper.like("subject",username);
                break;
        }
        return page(iPage,queryWrapper);
    }

    @Override
    public Teacher findUser(String username) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }
}
