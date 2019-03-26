package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.Info;
import com.example.springbootdemo.entity.UserInfo;

import java.util.List;

public interface InfoService {

    /**
     * 根据位置获取指定范围内，指定数量的信息
     * @param x
     * @param y
     * @param range
     * @param number
     * @return
     */
    public List<Info> findInfoList(Long infoType, String title, double x, double y, double range, int number);

    int addInfo(String title);
}
