package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Tropinone
 * @since 2022-07-30
 */
@Getter
@Setter
  @TableName("sys_allusion")
@ApiModel(value = "Allusion对象", description = "")
public class Allusion implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
      @TableId(type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("收到典故的老师")
      private String teacher;

      @ApiModelProperty("典故内容")
      private String allusion;


}
