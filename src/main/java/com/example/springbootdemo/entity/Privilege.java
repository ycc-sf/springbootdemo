package com.example.springbootdemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Privilege", description = "权限")
public class Privilege {
    @ApiModelProperty("权限id")
    private Long id;
    @ApiModelProperty("权限名称")
    private String privilegeName;
    @ApiModelProperty("权限对应url")
    private String url;
}
