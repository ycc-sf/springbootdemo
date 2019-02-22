package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.Privilege;
import com.example.springbootdemo.entity.UserInfo;

import java.util.List;

public interface UserService {
    /**
     * 通过username获取用户详情（包含密码）
     * @param username
     * @return
     */
    UserInfo getUser(String username);

    /**
     * 获取所有权限
     * @return
     */
    List<Privilege> getAllPrivilege();
}
