package com.example.springbootdemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "Info", description = "信息")
public class Info {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("标题")
    private String infoTitle;
    @ApiModelProperty("详情")
    private String infoDetail;
    @ApiModelProperty("经度")
    private Double locationX;
    @ApiModelProperty("纬度")
    private Double locationY;
    @ApiModelProperty("类型id")
    private Double typeId;
    @ApiModelProperty("时间")
    private Timestamp createDate;
    @ApiModelProperty("是否单次可用。0：不是。1：是")
    private Timestamp isSingle;
    @ApiModelProperty("用户id")
    private Long userId;

}
