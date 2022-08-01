package com.example.springboot.mapper;

import com.example.springboot.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-29
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

}
