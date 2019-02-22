package com.example.springbootdemo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "demo")
@ApiModel(value = "DemoEntity", description = "测试实体类")
public class DemoEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(nullable = true, name = "name")
    private String name;
    @Column(nullable = true, name = "age")
    private String age;

}
