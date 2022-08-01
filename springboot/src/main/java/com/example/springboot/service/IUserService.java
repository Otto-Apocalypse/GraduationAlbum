package com.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.PasswordDTO;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-24
 */
public interface IUserService extends IService<User> {
    IPage<User> findPage(Integer pageNum, Integer pageSize, String optionValue,String username);

    UserDTO login(UserDTO userDTO);

    User findUser(String username);

    void updatePassword(PasswordDTO passwordDTO);
}
