package com.example.springbootdemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "PageRequestParams", description = "分页参数")
public class PageRequestParams {

    @ApiModelProperty("开始行数")
    private long startRow;
    @ApiModelProperty("单页数量")
    private long limit;

    private PageRequestParams(Long startRow, Long limit){
        this.startRow = startRow;
        this.limit = limit;
    }

    /**
     * 根据页码和单页数量计算分页参数
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static PageRequestParams of(Integer pageNo, Integer pageSize){
        Long startRow = Long.valueOf((pageNo - 1) * pageSize);
        Long limit = Long.valueOf((pageSize));
        return new PageRequestParams(startRow , limit);
    }

}
