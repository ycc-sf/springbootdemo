package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.entity.UserRole;

import java.util.List;

public interface UserService {
    /**
     * 登录
     * @return
     */
    UserRole login(UserInfo userInfo);
}
