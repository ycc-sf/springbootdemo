package com.example.springbootdemo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "demo")
@ApiModel(value = "DemoEntity", description = "测试实体类")
public class DemoEntity {
    @Id
    private Long id;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private String age;

}
