package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.dao.DemoDao;
import com.example.springbootdemo.entity.DemoEntity;
import com.example.springbootdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public List<DemoEntity> getAll() {
        return demoDao.getAll();
    }
}
