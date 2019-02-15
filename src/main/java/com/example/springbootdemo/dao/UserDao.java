package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.Privilege;
import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    List<User> findUserByUsername(@Param("username") String username);

    /**
     * 获取所有权限
     * @return
     */
    List<Privilege> getAllPrivilege();
 }