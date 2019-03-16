package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.common.BusinessException;
import com.example.springbootdemo.common.ErrorCode;
import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.entity.UserRole;
import org.apache.commons.lang.StringUtils;
import com.example.springbootdemo.dao.UserDao;
import com.example.springbootdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserRole login(UserInfo userInfo) {
        logger.info("[service-begin]登录：{}", userInfo);
        if(userInfo == null){
            throw new BusinessException(ErrorCode.E_101002);
        }
        if(StringUtils.isBlank(userInfo.getUsername())){
            throw new BusinessException(ErrorCode.E_101002);
        }
        List<UserInfo> users = userDao.findUserByUsername(userInfo.getUsername());
        logger.info("[service-]用户名：{}。获取到用户：{}", userInfo.getUsername(), users);
        if(users == null || users.size() == 0){
            throw new BusinessException(ErrorCode.E_101003);
        }
        if(users.size() > 1){
            throw new BusinessException(ErrorCode.E_101004);
        }
        //校验密码
        if(!users.get(0).getPassword().equals(userInfo.getPassword())){
            throw new BusinessException(ErrorCode.E_101007);
        }
        logger.info("[service-]用户名密码校验通过。{}", users.get(0));
        //获取角色
        Role role = userDao.findRoleById(users.get(0).getRoleId());
        logger.info("[service-]获取角色。{}", role);
        if(role == null){
            throw new BusinessException(ErrorCode.E_101005);
        }
        UserRole userRole = new UserRole();
        userRole.setId(users.get(0).getId());
        userRole.setUsername(users.get(0).getUsername());
        userRole.setRealName(users.get(0).getRealName());
        userRole.setRoleId(users.get(0).getRoleId());
        userRole.setRoleName(role.getRoleName());
        userRole.setLevel(role.getLevel());
        logger.info("[service-end]登录。{}", userRole);
        return userRole;
    }

}
