package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

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

    @Override
    public User findUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }

}
