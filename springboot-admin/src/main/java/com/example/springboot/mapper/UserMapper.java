package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

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
