package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.DemoEntity;
import com.example.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DemoDao {

//    @Insert("insert into t_demo(tname) "+
//            "values(#{name})")
//    int save(Demo2  demo);

//    @Select("select * from demo")
    List<DemoEntity> getAll();

 }