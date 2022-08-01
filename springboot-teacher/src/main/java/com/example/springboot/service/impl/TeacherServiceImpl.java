package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.PasswordDTO;
import com.example.springboot.controller.dto.TeacherDTO;
import com.example.springboot.entity.Teacher;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.TeacherMapper;
import com.example.springboot.service.ITeacherService;
import com.example.springboot.utils.TokenUtils;
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
    public TeacherDTO login(TeacherDTO teacherDTO) {
        Teacher one = getTeacherInfo(teacherDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, teacherDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            teacherDTO.setToken(token);
            teacherDTO.setPassword(SecureUtil.md5(teacherDTO.getPassword()));
            return teacherDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public Teacher findUser(String username) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }

    @Override
    public void updatePassword(PasswordDTO passwordDTO) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",passwordDTO.getUsername());
        queryWrapper.eq("password",passwordDTO.getPassword());
        if(list(queryWrapper).size() < 1) throw new ServiceException(Constants.CODE_600, "密码错误");
        try{
            Teacher one = getOne(queryWrapper);
            one.setPassword(passwordDTO.getNewPassword());
            saveOrUpdate(one);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_600,"系统错误");
        }
    }

    private Teacher getTeacherInfo(TeacherDTO teacherDTO) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", teacherDTO.getUsername());
        queryWrapper.eq("password", teacherDTO.getPassword());
        Teacher one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
