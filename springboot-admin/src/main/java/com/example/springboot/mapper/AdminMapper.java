package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-31
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
