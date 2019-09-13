package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.entity.Info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoDao {

    /**
     * 根据位置获取指定范围内，指定数量的信息
     * @param x
     * @param y
     * @param range
     * @param number
     * @return
     */
    List<Info> selectRangeInfo(@Param("infoType") Long infoType, @Param("title") String title,
                               @Param("x") double x, @Param("y") double y,
                               @Param("range") double range, @Param("number") int number);

    int insertInfo(@Param("title") String title);
}