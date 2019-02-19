package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.common.BusinessException;
import com.example.springbootdemo.common.ErrorCode;
import com.example.springbootdemo.dao.DemoDao;
import com.example.springbootdemo.jpaRepository.DemoEntityRepository;
import com.example.springbootdemo.entity.DemoEntity;
import com.example.springbootdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Autowired
    private DemoEntityRepository demoEntityRepository;

    @Override
    public DemoEntity findById(Long id) {
        Optional<DemoEntity> demoEntity = demoEntityRepository.findById(id);
        if(demoEntity.get() == null){
            throw new BusinessException(ErrorCode.E_101003);
        }
        return demoEntity.get();
    }

    @Override
    public DemoEntity findByName(String name) {
        return demoEntityRepository.findByName(name);
    }

    @Override
    public DemoEntity findByNameAndAge(String name, int age) {
        return demoEntityRepository.findByNameAndAge(name, age);
    }

    @Override
    public List<DemoEntity> findAll() {
        return demoEntityRepository.findAll();
    }

}
