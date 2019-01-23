package com.example.springbootdemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
public class SwaggerConfig{

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors
                        .basePackage("com.example"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * @param
     * @return springfox.documentation.service.ApiInfo
     * @Title: 构建API基本信息
     * @methodName: buildApiInfo
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder().title("API文档")
                .description("DEMO api")
                .version("1.0").build();

    }

}
