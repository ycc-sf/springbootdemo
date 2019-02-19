package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.DemoEntity;

import java.util.List;

public interface DemoService {

    DemoEntity findById(Long id);

    DemoEntity findByName(String name);

    DemoEntity findByNameAndAge(String name, int age);

    List<DemoEntity> findAll();
}
