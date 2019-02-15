package com.example.springbootdemo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.Entity;

@Data
@Entity
@ApiModel(value = "DemoEntity", description = "测试实体类")
public class DemoEntity {
    private Integer id;
    private String name;
}
