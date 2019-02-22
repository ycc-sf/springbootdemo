package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.DemoEntity;

import java.util.List;

public interface DemoDao {

//    @Insert("insert into t_demo(tname) "+
//            "values(#{name})")
//    int save(Demo2  demo);

//    @Select("select * from demo")
    List<DemoEntity> getAll();

 }