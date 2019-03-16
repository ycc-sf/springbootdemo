package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.common.BusinessException;
import com.example.springbootdemo.common.ErrorCode;
import com.example.springbootdemo.dao.AdminUserDao;
import com.example.springbootdemo.dao.UserDao;
import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.service.AdminUserService;
import com.example.springbootdemo.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    @Autowired
    private AdminUserDao adminUserDao;

}
