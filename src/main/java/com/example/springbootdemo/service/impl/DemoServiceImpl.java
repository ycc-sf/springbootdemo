package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.common.BusinessException;
import com.example.springbootdemo.common.ErrorCode;
import com.example.springbootdemo.jpaRepository.DemoEntityRepository;
import com.example.springbootdemo.entity.DemoEntity;
import com.example.springbootdemo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

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
        log.info(name);
        return demoEntityRepository.findByName(name);
    }

    @Override
    public DemoEntity findByNameAndAge(String name, int age) {
        return demoEntityRepository.findByNameAndAge(name, String.valueOf(age));
    }

    @Override
    public List<DemoEntity> findAll() {
        return demoEntityRepository.findAll();
    }

}
