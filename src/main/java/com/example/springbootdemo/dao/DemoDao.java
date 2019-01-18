package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DemoDao {

//    @Insert("insert into t_demo(tname) "+
//            "values(#{name})")
//    int save(Demo2  demo);

    @Select("select * from demo")
    List<Student> getStudents();
 }