package com.example.springbootdemo.web;

import com.example.springbootdemo.common.RestResponse;
import com.example.springbootdemo.entity.DemoEntity;
import com.example.springbootdemo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @ApiOperation(value="通过id获取")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="path")
    @GetMapping("/id/{id}")
    public RestResponse<DemoEntity> findById(@PathVariable("id") Long id){
        log.info("[进入]{}", id);
        DemoEntity demoEntity = demoService.findById(id);
        log.info("[结果]{}", demoEntity);
        return RestResponse.success(demoEntity);
    }

    @ApiOperation(value="通过名字获取")
    @ApiImplicitParam(name = "name", value = "名字", required = true, dataType = "String", paramType="path")
    @GetMapping("/name/{name}")
    public RestResponse<DemoEntity> findByName(@PathVariable("name") String name){
        log.info("[进入]{}", name);
        DemoEntity demoEntity = demoService.findByName(name);
        log.info("[结果]{}", demoEntity);
        return RestResponse.success(demoEntity);
    }

    @ApiOperation(value="通过名字和年龄获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名字", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "int", paramType = "path")
    })
    @GetMapping("/name/{name}/age/{age}")
    public RestResponse<DemoEntity> findByNameAndAge(@PathVariable("name") String name,
                                                     @PathVariable("age") int age){
        log.info("[进入]{}, {}", name, age);
        DemoEntity demoEntity = demoService.findByNameAndAge(name, age);
//        DemoEntity demoEntity = new DemoEntity();
        log.info("[结果]{}", demoEntity);
        return RestResponse.success(demoEntity);
    }

    @ApiOperation(value="获取所有信息")
    @GetMapping("/all")
    public RestResponse<List<DemoEntity>> getAll(){
        log.info("[进入gelAll]");
        List<DemoEntity> all = demoService.findAll();
        log.info("[结果]{}", all);
        return RestResponse.success(all);
    }
}
