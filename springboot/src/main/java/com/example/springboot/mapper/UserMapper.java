package com.example.springboot.mapper;

import com.example.springboot.controller.dto.PasswordDTO;
import com.example.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
