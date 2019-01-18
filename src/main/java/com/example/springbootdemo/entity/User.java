package com.example.springbootdemo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String phoneNum;
    private Timestamp createDate;

}
