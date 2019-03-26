package com.example.springbootdemo.web;

import com.example.springbootdemo.common.RestResponse;
import com.example.springbootdemo.entity.Info;
import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.service.InfoService;
import com.example.springbootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/info")
@Api(value = "info", description = "信息接口")
public class InfoController {

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;

    @ApiOperation(value="根据位置获取指定范围内，指定数量的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "infoType", value = "信息类型", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "名称（模糊搜索）", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "x", value = "经度（数值较大的）", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "y", value = "纬度（数值较小的）", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "range", value = "要获取信息的半径", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "number", value = "要获取信息的数量", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping(value = "/findRangedInfoList")
    public RestResponse<List<Info>> findInfoList(@RequestParam(value="infoType", required=false) Long infoType,
                                                 @RequestParam(value="title", required=false) String title,
                                                 @RequestParam("x") Double x, @RequestParam("y") Double y,
                                          @RequestParam("range") Double range, @RequestParam("number") Integer number){
        logger.info("[begin]title:{} infoType:{} x:{} y:{}",title, infoType, x, y);
        List<Info> infoList = infoService.findInfoList(infoType, title, x, y, range, number);
        logger.info("[end]成功。");
        return RestResponse.success(infoList);
    }

    @ApiOperation(value="新增信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "名称（模糊搜索）", required = false, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/addInfo")
    public RestResponse<Nullable> addInfo(@RequestBody Info info){
        logger.info("[begin]");
        infoService.addInfo(info.getInfoTitle());
        logger.info("[end]成功。");
        return RestResponse.success();
    }

}

