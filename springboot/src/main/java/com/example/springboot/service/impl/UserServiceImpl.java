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
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();

    @Resource
    UserMapper userMapper;

    public IPage<User> findPage(Integer pageNum, Integer pageSize, String optionValue,String username){
        IPage<User> iPage = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        switch (optionValue){
            case "username":
                queryWrapper.like("username",username);
                break;
            case "name":
                queryWrapper.like("name",username);
                break;
            case "qq":
                queryWrapper.like("qq",username);
                break;
            case "phone":
                queryWrapper.like("phone",username);
                break;
            case "sex":
                queryWrapper.like("sex",username);
                break;
            case "wechat":
                queryWrapper.like("wechat",username);
                break;
            case "school":
                queryWrapper.like("school",username);
                break;
            case "subject":
                queryWrapper.like("subject",username);
                break;
        }
        return page(iPage,queryWrapper);
    }

    public UserDTO login(UserDTO userDTO){
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User findUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }

    @Override
    public void updatePassword(PasswordDTO passwordDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",passwordDTO.getUsername());
        queryWrapper.eq("password",passwordDTO.getPassword());
        if(list(queryWrapper).size() < 1) throw new ServiceException(Constants.CODE_600, "密码错误");
        try{
            User one = getOne(queryWrapper);
            one.setPassword(passwordDTO.getNewPassword());
            saveOrUpdate(one);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_600,"系统错误");
        }
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
