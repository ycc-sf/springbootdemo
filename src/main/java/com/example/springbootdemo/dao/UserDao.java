package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao {

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    List<UserInfo> findUserByUsername(@Param("username") String username);

    /**
     * 通过角色id查找角色
     * @param id
     * @return
     */
    Role findRoleById(@Param("id") Long id);
 }