package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.common.BusinessException;
import com.example.springbootdemo.common.ErrorCode;
import com.example.springbootdemo.entity.Privilege;
import org.apache.commons.lang.StringUtils;
import com.example.springbootdemo.dao.UserDao;
import com.example.springbootdemo.entity.User;
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
    public User getUser(String username) {
        logger.info("[service-begin]查询用户：{}", username);
        if(StringUtils.isBlank(username)){
            throw new BusinessException(ErrorCode.E_101002);
        }
        List<User> users = userDao.findUserByUsername(username);
        logger.info("[service-running]用户名：{}。获取到用户：{}", username, users);
        if(users == null || users.size() == 0){
            throw new BusinessException(ErrorCode.E_101003);
        }
        if(users.size() > 1){
            throw new BusinessException(ErrorCode.E_101004);
        }
        logger.info("[service-end]获取用户成功。{}", users.get(0));
        return users.get(0);
    }

    @Override
    public List<Privilege> getAllPrivilege() {
        logger.info("[service-begin]获取所有权限");
        List<Privilege> allPrivilege = userDao.getAllPrivilege();
        logger.info("[service-end]获取所有权限成功：{}", allPrivilege);
        return allPrivilege;
    }
}
