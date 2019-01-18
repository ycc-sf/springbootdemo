package com.example.springbootdemo.web;

import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/demo")
@Api(description = "demo接口")
public class HelloDemo {

    @Autowired
    private DemoService demoService;

    @ApiOperation(value="输出hello")
    @RequestMapping("/hello")
    public String say() {
        System.out.println("Hello springboot");
        return "hello,this is a springboot demo";
    }

    @ApiOperation(value="获取所有信息")
    @RequestMapping("/getAll")
    public List<Student> getAll(){
        List<Student> students = demoService.getStudents();
        log.info("[结果]{}", students);
        return students;
    }
}
