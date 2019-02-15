package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.DemoEntity;

import java.util.List;

public interface DemoService {
    List<DemoEntity> getAll();
}
