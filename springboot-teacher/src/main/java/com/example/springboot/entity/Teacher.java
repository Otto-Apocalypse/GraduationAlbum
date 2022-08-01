package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-29
 */
@Getter
@Setter
@TableName("sys_teacher")
@ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码") private String password;

    @ApiModelProperty("教师姓名")
    private String name;

    @ApiModelProperty("教师生日")
    private String birthday;

    @ApiModelProperty("教师教授学科")
    private String subject;

    @ApiModelProperty("教师的手机号")
    private String phone;
}
