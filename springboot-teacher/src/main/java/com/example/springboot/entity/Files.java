package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "files操作对象", description = "")
@TableName("sys_file")
public class Files {

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private String id;

    @ApiModelProperty("文件名")
    private String name;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("文件储存位置,网络请求的Url")
    private String url;

    @ApiModelProperty("md5加密,文件的唯一标识")
    private String md5;

    @ApiModelProperty("文件是否被删除")
    private boolean isDelete;

    @ApiModelProperty("文件是否可用")
    private boolean enable;

}
