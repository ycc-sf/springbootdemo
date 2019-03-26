package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.common.BusinessException;
import com.example.springbootdemo.common.ErrorCode;
import com.example.springbootdemo.dao.InfoDao;
import com.example.springbootdemo.dao.UserDao;
import com.example.springbootdemo.entity.Info;
import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.service.InfoService;
import com.example.springbootdemo.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    private static final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    @Autowired
    private InfoDao infoDao;

    @Override
    public List<Info> findInfoList(Long infoType, String title, double x, double y, double range, int number) {
        List<Info> infoList = infoDao.selectRangeInfo(infoType, title, x, y, range, number);
        return infoList;
    }

    @Override
    public int addInfo(String title) {
        int info = infoDao.insertInfo(title);
        return info;
    }
}
