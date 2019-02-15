package com.example.springbootdemo.web;

import com.example.springbootdemo.common.RestResponse;
import com.example.springbootdemo.entity.DemoEntity;
import com.example.springbootdemo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/demo")
@Api(value = "demo", description = "示例接口")
public class HelloDemo {

    @Autowired
    private DemoService demoService;

    @ApiOperation(value="输出hello")
    @GetMapping("/hello")
    public String say() {
        System.out.println("Hello springboot");
        return "hello,this is a springboot demo";
    }

    @ApiOperation(value="获取所有信息")
    @GetMapping("/getAll")
    public RestResponse<List<DemoEntity>> getAll(){
        log.info("[进入gelAll]");
        List<DemoEntity> students = demoService.getAll();
        log.info("[结果]{}", students);
        return RestResponse.success(students);
    }
}
