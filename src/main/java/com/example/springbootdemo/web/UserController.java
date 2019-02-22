package com.example.springbootdemo.web;

import com.example.springbootdemo.common.RestResponse;
import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@Api(value = "user", description = "用户接口")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value="测试接口")
    @GetMapping("/hello")
    public String say() {
        logger.info("Hello springboot");
        return "hello,this is a springbootdemo";
    }

    @ApiOperation(value="通过用户名查询用户信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType="path")
    @GetMapping(value = "/{username}")
    public RestResponse<UserInfo> getUser(@PathVariable("username") String username){
        logger.info("[web-begin]查询用户：{}", username);
        UserInfo user = userService.getUser(username);
        logger.info("[web-end]获取用户成功。{}", user);
        return RestResponse.success(user);
    }


}
